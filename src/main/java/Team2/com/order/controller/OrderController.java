package Team2.com.order.controller;

import Team2.com.order.dto.OrderRequestDto;
import Team2.com.order.dto.OrderResponseDto;
import Team2.com.order.dto.OrderResultDto;
import Team2.com.order.service.OrderService;
import Team2.com.security.details.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "주문")
public class OrderController {
    private final OrderService orderService;


    @ApiOperation(value = "주문하기")
    @PostMapping("/customer/orders")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
    public HttpStatus createOrder(@RequestBody OrderRequestDto requestOrderDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.order(requestOrderDto.getItems(), userDetails.getMember());
        return HttpStatus.OK;
    }


    @GetMapping("/customer/orders")
    @ApiOperation(value = "내 주문 목록 조회")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
    public ResponseEntity<List<OrderResultDto>> getOrders(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal UserDetailsImpl userDetails){
        OrderResultDto orders = orderService.getOrders(offset, limit, userDetails.getMember());
        return new ResponseEntity(orders, HttpStatus.OK);
    }



    //나(판매자)의 모든 주문 목록 list 조회
    @GetMapping("/seller/orders")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @ApiOperation(value = "판매자 주문 목록 조회 - 어떤 사용자가 주문을 넣었는지")
    public ResponseEntity getAllCustomerBuyList(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal UserDetailsImpl userDetails){
        OrderResultDto orderAllList = orderService.getAllCustomerBuyList(offset, limit, userDetails.getUsername());
        return new ResponseEntity(orderAllList, HttpStatus.OK);
    }

    //주문번호로 주문 조회
    @GetMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @ApiOperation(value = "주문 번호로 조회")
    public ResponseEntity getCustomerBuyItem(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        OrderResponseDto orderDto = orderService.getCustomerBuyItem(orderId, userDetails.getUsername());
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    //판매자 주문 완료 처리
    @PutMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @ApiOperation(value = "주문 완료 처리")
    public ResponseEntity orderCompleteProceeding(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.orderCompleteProceeding(orderId, userDetails.getUsername());
        return new ResponseEntity("주문처리가 완료되었습니다.", HttpStatus.OK);
    }

}

