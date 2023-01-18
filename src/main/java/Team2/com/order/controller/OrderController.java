//package Team2.com.order.controller;
//
//import Team2.com.order.dto.OrderRequestDto;
//import Team2.com.order.dto.OrderResponseDto;
//import Team2.com.order.dto.OrderResultDto;
//import Team2.com.order.service.OrderService;
//import Team2.com.order.service.OrderServiceImpl;
//import Team2.com.security.details.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class OrderController {
//    private final OrderServiceImpl orderService;
//    @PostMapping("/customer/orders")
//    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
//    public HttpStatus createOrder(@RequestBody OrderRequestDto requestOrderDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        orderService.order(requestOrderDto.getItems(), userDetails.getMember());
//        return HttpStatus.OK;
//
//    }
//
//    @GetMapping("/customer/orders")
//    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
//    public ResponseEntity<List<OrderResultDto>> getOrders(@RequestParam int offset, @RequestParam int limit){
//        OrderResultDto orders = orderService.getOrders(offset, limit);
//        return new ResponseEntity(orders, HttpStatus.OK);
//    }
//
//    //나(판매자)의 모든 주문 목록 list 조회
//    @GetMapping("/seller/orders")
//    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
//    public ResponseEntity getAllCustomerBuyList(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        OrderResultDto orderAllList = orderService.getAllCustomerBuyList(offset, limit, userDetails.getUsername());
//        return new ResponseEntity(orderAllList, HttpStatus.OK);
//    }
//
//    //주문번호로 주문 조회
//    @GetMapping("/seller/order/{id}")
//    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
//    public ResponseEntity getCustomerBuyItem(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        OrderResponseDto orderDto = orderService.getCustomerBuyItem(orderId, userDetails.getUsername());
//        return new ResponseEntity(orderDto, HttpStatus.OK);
//    }
//
//    //판매자 주문 완료 처리
//    @PutMapping("/seller/order/{id}")
//    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
//    public ResponseEntity orderCompleteProceeding(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        orderService.orderCompleteProceeding(orderId, userDetails.getUsername());
//        return new ResponseEntity("주문처리가 완료되었습니다.", HttpStatus.OK);
//    }
//
//}
