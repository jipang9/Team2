package Team2.com.order.controller;


import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.entity.Order;
import Team2.com.order.repository.OrderRepository;
import Team2.com.order.service.OrderService;
import Team2.com.orderItem.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public void createOrder(){
        orderService.createOrder();
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
}
