package Team2.com.member.controller;

import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.dto.member.LoginRequestDto;
import Team2.com.member.dto.member.MsgResponseDto;
import Team2.com.member.dto.member.SignupRequestDto;
import Team2.com.member.service.member.MemberService;
import Team2.com.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    private final MemberService memberService;

    //회원가입 구현
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료", HttpStatus.OK.value()));
    }

    @PostMapping("/apply")
    public HttpStatus apply(@RequestBody ApplyRequestDto applyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsimpl){
        memberService.apply(applyRequestDto, userDetailsimpl.getMember());
        return HttpStatus.OK;
    }

}