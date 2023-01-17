package Team2.com.order.controller;


import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.repository.MemberRepository;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.service.OrderService;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    private static final Member seller = new Member("판매자", "1234", MemberRoleEnum.SELLER);
    private static final Member customer = new Member("구매자", "1234", MemberRoleEnum.CUSTOMER);
    private static final Item item1 = new Item("칫솔", "이닦는 도구", seller, 3000, 100);
    private static final Item item2 = new Item("연필", "글쓰는 도구", seller,1000, 100);
    private static final Item item3 = new Item("신발", "신는거", seller, 60000, 100);

    @PostMapping("/customer/orders")
    public ResponseEntity createOrder(@RequestBody OrderDto.Request requestOrderDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if(token != null){
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            orderService.order(requestOrderDto.getItems(), member);
            return new ResponseEntity("주문이 완료되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity("주문을 실패했습니다.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/customer/orders")
    public ResponseEntity<List<OrderDto.Result>> getOrders(@RequestParam int offset, @RequestParam int limit){
        OrderDto.Result orders = orderService.getOrders(offset, limit);
        return new ResponseEntity(orders, HttpStatus.OK);
    }


    //나(판매자)의 모든 주문 목록 list 조회
    @GetMapping("/seller/orders")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getAllCustomerBuyList(@RequestParam("page") Integer page, @AuthenticationPrincipal UserDetails userDetails){
        PageRequest pageRequest = PageRequest.of(page,10);

        List<OrderDto.Response> orderAllList = orderService.getAllCustomerBuyList(pageRequest, userDetails.getUsername());

        if(orderAllList.isEmpty()){
            return new ResponseEntity("주문 내역이 존재 하지않습니다.", HttpStatus.OK);
        }
        return new ResponseEntity(orderAllList, HttpStatus.OK);
    }

    //주문번호로 주문 조회
    @GetMapping("/seller/order/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getCustomerBuyItem(@PathVariable("id") Long orderId, @AuthenticationPrincipal UserDetails userDetails){
        OrderDto.Response orderDto = orderService.getCustomerBuyItem(orderId, userDetails.getUsername());
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
