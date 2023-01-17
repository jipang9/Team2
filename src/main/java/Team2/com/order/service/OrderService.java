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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ItemRepository itemRepository;

    public void order(List<OrderDto.RequestItemDto> items, Member member) {
        // 1. 주문할 상품 불러오기
        for (OrderDto.RequestItemDto item : items) {
            Item findItem = itemRepository.findById(item.getId()).orElseThrow(
                    () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
            );

            // 2. OrderItem 만들기
            OrderItems orderItems = OrderItems.createOrderItems(findItem, item.getCount());
            orderItemsRepository.saveAndFlush(orderItems);

        }

        List<OrderItems> orderItems = orderItemsRepository.findAll();

        // 3. Order 테이블에 저장하기
        Order order = Order.createOrder(member, orderItems);
        orderRepository.saveAndFlush(order);
    }

    public List<OrderDto.Response> getOrders() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
        Page<Order> page = orderRepository.findAll(pageRequest);
        Page<OrderDto.Response> map = page.map(order -> new OrderDto.Response(order.getId(), order.getMember().getUsername(), order.getOrderItems()));
        List<OrderDto.Response> content = map.getContent();

        // List<OrderDto.ResponseOrderDto> collect = page.stream()
        //         .map(v -> new OrderDto.ResponseOrderDto(v.getId(), v.getMember().getUsername(), v.getOrderItems()))
        //         .collect(Collectors.toList());

        return content;
    }
}
