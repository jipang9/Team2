package Team2.com.order.controller;


import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.entity.Order;
import Team2.com.order.repository.OrderRepository;
import Team2.com.order.service.OrderService;
import Team2.com.orderItem.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class OrderController {
    private final OrderService orderService;

    private static final Member seller = new Member("판매자");
    private static final Member customer = new Member("구매자");
    private static final Item item1 = new Item("칫솔", "이닦는 도구", seller, 3000, 100);
    private static final Item item2 = new Item("연필", "글쓰는 도구", seller,1000, 100);
    private static final Item item3 = new Item("신발", "신는거", seller, 60000, 100);

    @PostMapping("/orders")
    public void createOrder(){
        // memberRepository.save(seller);
        // memberRepository.save(customer);

        // itemRepository.save(item1);
        // itemRepository.save(item2);
        // itemRepository.save(item3);
        orderService.createOrder(seller, customer, item1, item2, item3);
    }

    @GetMapping("/orders")
    public List<OrderDto.ResponseOrderDto> getOrders(){
        return orderService.getOrders();
    }
}
