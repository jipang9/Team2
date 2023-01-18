package Team2.com.order.controller;

import Team2.com.order.dto.OrderRequestDto;
import Team2.com.order.dto.OrderResponseDto;
import Team2.com.order.dto.OrderResultDto;
import Team2.com.order.service.OrderService;
import Team2.com.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/customer/orders")
    public ResponseEntity createOrder(@RequestBody OrderRequestDto requestOrderDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.order(requestOrderDto.getItems(), userDetails.getMember());
        return new ResponseEntity("주문이 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/customer/orders")
    public ResponseEntity<List<OrderResultDto>> getOrders(@RequestParam int offset, @RequestParam int limit){
        OrderResultDto orders = orderService.getOrders(offset, limit);
        return new ResponseEntity(orders, HttpStatus.OK);
    }


    //나(판매자)의 모든 주문 목록 list 조회
    @GetMapping("/seller/orders")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getAllCustomerBuyList(@RequestParam("page") Integer page, @AuthenticationPrincipal UserDetails userDetails){
        PageRequest pageRequest = PageRequest.of(page,10);
        List<OrderResponseDto> orderAllList = orderService.getAllCustomerBuyList(pageRequest, userDetails.getUsername());
        if(orderAllList.isEmpty()){
            return new ResponseEntity("주문 내역이 존재 하지않습니다.", HttpStatus.OK);
        }
        return new ResponseEntity(orderAllList, HttpStatus.OK);
    }

    //주문번호로 주문 조회
    @GetMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getCustomerBuyItem(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetails userDetails){
        OrderResponseDto orderDto = orderService.getCustomerBuyItem(orderId, userDetails.getUsername());
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    //판매자 주문 완료 처리
    @PutMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity orderCompleteProceeding(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetails userDetails){
        orderService.orderCompleteProceeding(orderId, userDetails.getUsername());
        return new ResponseEntity("주문처리가 완료되었습니다.", HttpStatus.OK);
    }
}
