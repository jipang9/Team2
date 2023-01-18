package Team2.com.member.controller;

import Team2.com.member.dto.InfoResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.*;
import Team2.com.member.service.member.MemberService;
import Team2.com.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info") //내 프로필 조회
    public InfoResponseDto myInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return memberService.getMyInfo(userDetails.getMember());
    }

    @PostMapping("/signup") //회원가입 구현
    public ResponseEntity<MsgResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/login") //로그인 구현
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/apply") // 사용자 권한 요청
    public HttpStatus apply(@RequestBody ApplyRequestDto applyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsimpl) {
        memberService.apply(applyRequestDto, userDetailsimpl.getMember());
        return HttpStatus.OK;
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellersResponseDto>> getSellerList(@RequestParam(defaultValue = "0") String sellerId) {
        List<SellersResponseDto> sellerLists;
        if (sellerId.equals("0")) {
            sellerLists = memberService.getSellerLists();
        } else {
            sellerLists = memberService.getSellerOne(sellerId);
        }
        return new ResponseEntity(sellerLists, HttpStatus.OK);
    }

}