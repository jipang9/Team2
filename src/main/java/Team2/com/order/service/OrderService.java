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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ItemRepository itemRepository;

    public void createOrder(Member seller, Member customer, Item item1, Item item2, Item item3) {
        memberRepository.saveAndFlush(seller);
        memberRepository.saveAndFlush(customer);

        itemRepository.saveAndFlush(item1);
        itemRepository.saveAndFlush(item2);
        itemRepository.saveAndFlush(item3);


        OrderItems orderItems1 = new OrderItems(item1, 3);
        OrderItems orderItems2 = new OrderItems(item2, 2);
        OrderItems orderItems3 = new OrderItems(item3, 4);
        orderItemsRepository.saveAndFlush(orderItems1);
        orderItemsRepository.saveAndFlush(orderItems2);

        orderRepository.saveAndFlush(new Order(customer, orderItems1));
        orderRepository.saveAndFlush(new Order(customer, orderItems2));
        orderRepository.saveAndFlush(new Order(customer, orderItems3));

        // orderRepository.save(order3);
        // orderRepository.save(order4);
        // Order findOrder = orderRepository.findById(save.getId()).get();

        // System.out.println("findOrder = " + findOrder);
    }

    public List<OrderDto.ResponseOrderDto> getOrders() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
        Page<Order> page = orderRepository.findAll(pageRequest);
        Page<OrderDto.ResponseOrderDto> map = page.map(order -> new OrderDto.ResponseOrderDto(order.getId(), order.getMember().getUsername(), order.getOrderItems()));
        List<OrderDto.ResponseOrderDto> content = map.getContent();

        // List<OrderDto.ResponseOrderDto> collect = page.stream()
        //         .map(v -> new OrderDto.ResponseOrderDto(v.getId(), v.getMember().getUsername(), v.getOrderItems()))
        //         .collect(Collectors.toList());

        return content;
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
