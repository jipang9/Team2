package Team2.com.order.controller;


import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.repository.MemberRepository;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.service.OrderService;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.orderItem.entity.OrderItems;
import Team2.com.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
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

    @PostMapping("/orders")
    public void createOrder(@RequestBody OrderDto.Request requestOrderDto, HttpServletRequest request){
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
            orderService.createOrder(requestOrderDto.getItems(), member);
        }
    }

    @GetMapping("/orders")
    public List<OrderDto.Response> getOrders(){
        return orderService.getOrders();
    }
}
