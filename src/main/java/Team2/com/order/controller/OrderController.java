package Team2.com.order.controller;

import Team2.com.member.entity.Member;
import Team2.com.member.repository.MemberRepository;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.service.OrderService;
import Team2.com.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class OrderController {
    private final OrderService orderService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/orders")
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

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto.Result>> getOrders(@RequestParam int offset, @RequestParam int limit){
        OrderDto.Result orders = orderService.getOrders(offset, limit);
        return new ResponseEntity(orders, HttpStatus.OK);
    }
}
