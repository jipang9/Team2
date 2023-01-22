package Team2.com.member.controller;

import Team2.com.member.dto.InfoResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.dto.member.LoginRequestDto;
import Team2.com.member.dto.member.MsgResponseDto;
import Team2.com.member.dto.member.SignupRequestDto;
import Team2.com.member.service.member.MemberService;
import Team2.com.order.service.OrderService;
import Team2.com.security.details.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Api(tags = "회원")
public class MemberController {

    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/info")
    @ApiOperation(value = "내 프로필 조회")
    public InfoResponseDto myInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return memberService.getMyInfo(userDetails.getMember());
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<MsgResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/apply")
    @ApiOperation(value = "권한 요청")
    public HttpStatus apply(@RequestBody ApplyRequestDto applyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsimpl) {
        memberService.apply(applyRequestDto, userDetailsimpl.getMember());
        return HttpStatus.OK;
    }

    @GetMapping("/sellers")
    @ApiOperation(value = "판매자 리스트 조회")
    public ResponseEntity<List<SellersResponseDto>> getSellerList(@RequestParam(defaultValue = "0") String sellerId){

        List<SellersResponseDto> sellerLists;
        if (sellerId.equals("0")) {
            sellerLists = memberService.getSellerLists();
        } else {
            sellerLists = memberService.getSellerOne(sellerId);
        }
        return new ResponseEntity(sellerLists, HttpStatus.OK);
    }



    @DeleteMapping("/apply-cancel")
    @ApiOperation(value = " 권한 취소 ")
    public HttpStatus cancelApply(@AuthenticationPrincipal UserDetailsImpl userDetails){
        memberService.cancelRequest(userDetails.getMember().getId());
        return HttpStatus.OK;
    }


    @DeleteMapping("order/{id}")
    @ApiOperation(value = " 주문 취소 ")
    public HttpStatus cancelOrder(@PathVariable("id")Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.cancelOrder(id,userDetails.getMember());
        return HttpStatus.OK;
    }

//    @DeleteMapping("/")
//    public HttpStatus withdrawal(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        memberService.
//
//    }


}