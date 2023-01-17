package Team2.com.order.service;

import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.repository.MemberRepository;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.entity.Order;
import Team2.com.order.repository.OrderRepository;
import Team2.com.orderItem.entity.OrderItems;
import Team2.com.orderItem.repository.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ItemRepository itemRepository;

    public void order(List<OrderDto.RequestItemDto> items, Member member) {
        // 1. 주문할 Item 불러오기
        ArrayList<Long> orderItemIds = new ArrayList();
        for (OrderDto.RequestItemDto item : items) {
            Item findItem = itemRepository.findById(item.getId()).orElseThrow(
                    () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
            );

            // 2. OrderItem 만들기
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

    public OrderDto.Result getOrders(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<Order> page = orderRepository.findAll(pageRequest);
        Page<OrderDto.Response> map = page.map(order -> new OrderDto.Response(order.getId(), order.getMember().getUsername(), order.getOrderItems()));
        List<OrderDto.Response> content = map.getContent(); // Order 배열
        long totalCount = map.getTotalElements(); // Order 전체 개수

        OrderDto.Result result = new OrderDto.Result(offset, totalCount, content);

        return result;
    }

    //(판매자) 주문내역 전체 조회
    public List<OrderDto.ResponseOrderDto> getAllCustomerBuyList(PageRequest pageRequest, String sellerName) {

        //판매자의 주문내역 조회
        Page<Order> page = orderRepository.findAll(pageRequest);

        Page<String> sellerList = page.map(order -> order.getOrderItems().get(0).getMember().getUsername());


        return ;
    }


    //(판매자)주문 내역 조회
    @Transactional(readOnly = true)
    public OrderDto.ResponseOrderDto getCustomerBuyItem(Long orderId, String sellerName) {

        //주문조회
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("주문내역이 존재하지 않습니다."));

        //판매자 상품이 맞는지 확인
        for(int i=0; i<order.getOrderItems().size(); i++){
            if(!order.getOrderItems().get(0).getItem().getMember().getUsername().equals(sellerName)){
                throw new IllegalArgumentException("판매자의 상품과 일치하지 않습니다.");
            }
        }
        return new OrderDto.ResponseOrderDto(order.getId(), order.getMember().getUsername(), order.getOrderItems());
    }

    //(판매자)주문완료처리
    @Transactional
    public void orderCompleteProceeding(Long orderId, String sellerName) {

        //주문조회
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("주문내역이 존재하지 않습니다."));

        //판매자 상품이 맞는지 확인
        for(int i=0; i<order.getOrderItems().size(); i++){
            if(!order.getOrderItems().get(0).getItem().getMember().getUsername().equals(sellerName)){
                throw new IllegalArgumentException("판매자의 상품과 일치하지 않습니다.");
            }
        }
        //주문상태 변경
        order.updateOrderStatus();
    }


}
