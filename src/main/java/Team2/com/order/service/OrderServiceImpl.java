package Team2.com.order.service;

import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.order.dto.OrderRequestItemDto;
import Team2.com.order.dto.OrderResponseDto;
import Team2.com.order.dto.OrderResultDto;
import Team2.com.order.entity.Order;
import Team2.com.order.repository.OrderRepository;
import Team2.com.orderItem.entity.OrderItems;
import Team2.com.orderItem.repository.OrderItemsRepository;
import Team2.com.security.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static Team2.com.security.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ItemRepository itemRepository;

    //주문하기
    @Transactional
    @Override
    public void order(List<OrderRequestItemDto> items, Member member) {
        // 1. 주문할 Item 불러오기
        ArrayList<Long> orderItemIds = new ArrayList();
        for (OrderRequestItemDto item : items) {
            Item findItem = itemRepository.findById(item.getId()).orElseThrow(
                    () -> new CustomException(NOT_FOUND_ITEM)
            );

            // 2. OrderItem 만들기
            if(item.getCount() <= 0){
                throw new CustomException(INVALID_ORDER_COUNT);
            }
            OrderItems orderItems = OrderItems.createOrderItems(findItem, item.getCount());
            OrderItems saveOrderItem = orderItemsRepository.saveAndFlush(orderItems);
            orderItemIds.add(saveOrderItem.getId());
        }

        // 3. OrderItem 가져오기
        List<OrderItems> orderItems = new ArrayList<>();

        for (Long orderItemId : orderItemIds) {
            Optional<OrderItems> findOrderItem = orderItemsRepository.findById(orderItemId);
            orderItems.add(findOrderItem.get());
        }

        // 4. Order 테이블에 저장하기
        Order order = Order.createOrder(member, orderItems);
        orderRepository.saveAndFlush(order);
    }

    // (고객) 주문내역 전체 조회
    @Transactional(readOnly = true)
    @Override
    public OrderResultDto getOrders(int offset, int limit, Member member) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<Order> page = orderRepository.findAllByMemberId(pageRequest, member.getId());
        Page<OrderResponseDto> map = page.map(order -> new OrderResponseDto(order.getId(), order.getOrderItems()));
        List<OrderResponseDto> content = map.getContent(); // Order 배열
        long totalCount = map.getTotalElements(); // Order 전체 개수

        OrderResultDto result = new OrderResultDto<>(offset, totalCount, content);

        return result;
    }

    //(판매자) 주문내역 전체 조회
    @Transactional
    @Override
    public OrderResultDto getAllCustomerBuyList(int offset, int limit, String sellerName) {

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));

        Page<Order> page = orderRepository.findAll(pageRequest);
        List<OrderResponseDto> resultList = new ArrayList<>();

        Iterator<Order> keys = page.iterator();
        while( keys.hasNext() ){
            Order key = keys.next();

            for(int i=0; i<key.getOrderItems().size(); i++){
                if(sellerName.equals(key.getOrderItems().get(i).getItem().getMember().getUsername())){
                    resultList.add(new OrderResponseDto(key.getId(), key.getOrderItems()));
                }
            }
        }
        Long totalCount = (long)resultList.size();

        return new OrderResultDto(offset, totalCount, resultList);
    }

    //(판매자)주문 내역 조회
    @Transactional(readOnly = true)
    @Override
    public OrderResponseDto getCustomerBuyItem(Long orderId, String sellerName) {

        //주문조회
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException(NOT_FOUND_ORDERNUMBER));

        //판매자 상품이 맞는지 확인
        for(int i=0; i<order.getOrderItems().size(); i++){
            if(!order.getOrderItems().get(0).getItem().getMember().getUsername().equals(sellerName)){
                throw new CustomException(INVALID_SELLER_ITEM);
            }
        }
        return new OrderResponseDto(order.getId(), order.getOrderItems());
    }

    //(판매자)주문완료처리
    @Transactional
    @Override
    public void orderCompleteProceeding(Long orderId, String sellerName) {

        //주문조회
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException(NOT_FOUND_ORDERNUMBER));

        //판매자 상품이 맞는지 확인
        for(int i=0; i<order.getOrderItems().size(); i++){
            if(!order.getOrderItems().get(0).getItem().getMember().getUsername().equals(sellerName)){
                throw new CustomException(INVALID_SELLER_ITEM);
            }
        }
        //주문상태 변경 : N->Y
        order.updateOrderStatus();

        //주문처리 완료 시 -> 해당 주문 내역 삭제
        if(order.getOrderStatus().equals("Y")){
            orderRepository.deleteById(orderId);
        }
    }
}
