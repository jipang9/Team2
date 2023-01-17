package Team2.com.order.controller;

import Team2.com.order.dto.OrderDto;
import Team2.com.order.service.OrderService;
import Team2.com.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody OrderDto.Request requestOrderDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.order(requestOrderDto.getItems(), userDetails.getMember());
        return new ResponseEntity("주문이 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto.Result>> getOrders(@RequestParam int offset, @RequestParam int limit){
        OrderDto.Result orders = orderService.getOrders(offset, limit);
        return new ResponseEntity(orders, HttpStatus.OK);
    }
}
