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

    public void createOrder() {
        // 1. 주문할 상품 불러오기

        // 2. OrderItem 만들기

        // 3. Order 만들어서 저장하기

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
}
