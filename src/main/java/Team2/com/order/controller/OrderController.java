package Team2.com.order.controller;


import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.service.OrderService;
import Team2.com.member.entity.MemberRoleEnum;
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

    private static final Member seller = new Member("판매자", "1234", MemberRoleEnum.SELLER);
    private static final Member customer = new Member("구매자", "1234", MemberRoleEnum.CUSTOMER);
    private static final Item item1 = new Item("칫솔", "이닦는 도구", seller, 3000, 100);
    private static final Item item2 = new Item("연필", "글쓰는 도구", seller,1000, 100);
    private static final Item item3 = new Item("신발", "신는거", seller, 60000, 100);

    @PostMapping("/customer/orders")
    public void createOrder(){
        // memberRepository.save(seller);
        // memberRepository.save(customer);

        // itemRepository.save(item1);
        // itemRepository.save(item2);
        // itemRepository.save(item3);
        orderService.createOrder(seller, customer, item1, item2, item3);
    }

    @GetMapping("/customer/orders")
    public List<OrderDto.ResponseOrderDto> getOrders(){
        return orderService.getOrders();
    }


    //나(판매자)의 모든 주문 목록 list 조회
    @GetMapping("/seller/orders")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getAllCustomerBuyList(@RequestParam("page") Integer page, @AuthenticationPrincipal UserDetails userDetails){
        PageRequest pageRequest = PageRequest.of(page,10);

        List<OrderDto.ResponseOrderDto> orderAllList = orderService.getAllCustomerBuyList(pageRequest, userDetails.getUsername());

        if(orderAllList.isEmpty()){
            return new ResponseEntity("주문 내역이 존재 하지않습니다.", HttpStatus.OK);
        }
        return new ResponseEntity(orderAllList, HttpStatus.OK);
    }

    //주문번호로 주문 조회
    @GetMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getCustomerBuyItem(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetails userDetails){
        OrderDto.ResponseOrderDto orderDto = orderService.getCustomerBuyItem(orderId, userDetails.getUsername());
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
