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

    public void createOrder(Member seller, Member customer, Item item1, Item item2, Item item3) {
        System.out.println("seller.getId() = " + seller.getId());
        System.out.println("customer.getId() = " + customer.getId());
        // Member seller = new Member("판매자");
        // Member customer = new Member("구매자");
        memberRepository.save(seller);
        memberRepository.save(customer);
        //
        // Item item1 = new Item("칫솔", "이닦는 도구", seller, 3000, 100);
        // Item item2 = new Item("연필", "글쓰는 도구", seller,1000, 100);
        // Item item3 = new Item("신발", "신는거", seller, 60000, 100);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        OrderItems orderItems1 = new OrderItems(item1, 3);
        OrderItems orderItems2 = new OrderItems(item2, 2);
        orderItemsRepository.save(orderItems1);
        orderItemsRepository.save(orderItems2);

        Order order = new Order(customer, orderItems1, orderItems2);
        Order save = orderRepository.save(order);
        Order findOrder = orderRepository.findById(save.getId()).get();

        // System.out.println("findOrder = " + findOrder);
    }

    public List<OrderDto.ResponseOrderDto> getOrders() {
        List<Order> all = orderRepository.findAll();
        List<OrderDto.ResponseOrderDto> collect = all.stream()
                .map(v -> new OrderDto.ResponseOrderDto(v.getId(), v.getMember().getUsername(), v.getOrderItems()))
                .collect(Collectors.toList());

        return collect;
    }
}
