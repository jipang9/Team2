package Team2.com.order.service;

import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.entity.Order;
import Team2.com.order.repository.OrderRepository;
import Team2.com.orderItem.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void createOrder() {
        Member member = new Member("철수");

        // em.persist(member);
        // Item item1 = new Item("칫솔", "이닦는 도구", 3000, 100);
        // Item item2 = new Item("연필", "글쓰는 도구", 1000, 100);
        // Item item3 = new Item("신발", "신는거", 60000, 100);
        // em.persist(item1);
        // em.persist(item2);
        // em.persist(item3);
        //
        // OrderItems orderItems1 = new OrderItems(item1, 3);
        // OrderItems orderItems2 = new OrderItems(item2, 2);
        // em.persist(orderItems1);
        // em.persist(orderItems2);
        //
        // Order order = new Order(member, orderItems1, orderItems2);
        //
        // em.persist(order);
        // em.flush();
    }
}
