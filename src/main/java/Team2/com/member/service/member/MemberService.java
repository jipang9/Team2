package Team2.com.member.service.member;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.Request;
import Team2.com.member.entity.Status;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.dto.member.LoginRequestDto;
import Team2.com.member.dto.member.SignupRequestDto;
import Team2.com.member.repository.RequestRepository;
import Team2.com.security.exception.CustomException;
import Team2.com.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static Team2.com.security.exception.ErrorCode.*;

@Service

@Validated
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final RequestRepository requestRepository;

    @Transactional
    public void signup(@Valid SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<Member> found = memberRepository.findByUsername(signupRequestDto.getUsername());
        if (found.isPresent()) {
            throw new CustomException(DUPLICATED_USERNAME);
        }

        MemberRoleEnum role = MemberRoleEnum.CUSTOMER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {

                throw new CustomException(INVALID_TOKEN);
            }
            role = MemberRoleEnum.ADMIN;
        }
        Member user = new Member(username, password, role);
        memberRepository.save(user);
    }

    //로그인 구현
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(NOT_MATCH_INFORMATION);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername(), member.getRole()));
    }

    public List<MembersResponseDto> getMemberLists() {
        return memberRepository.findAllByMembers();
    }

    public List<SellersResponseDto> getSellerLists() {
        return memberRepository.findAllBySellers();
    }

    public void apply(ApplyRequestDto applyRequestDto, Member member) {
        if(applyRequestDto.getStatus().equals("UP")) {
            Request request = new Request(member.getId(), member.getRole().toString(), applyRequestDto.getStatus());
            requestRepository.save(request);
        }else if(applyRequestDto.getStatus().equals("DOWN")){
            Request request = new Request(member.getId(), member.getRole().toString(), applyRequestDto.getStatus());
            requestRepository.save(request);
        }else{
            throw new IllegalStateException("등록 에러 발생");
        }
    }
    public List<SellersResponseDto> getSellerOne(String sellerId) {
        return memberRepository.findBySellerId(Long.valueOf(sellerId));
    }


}
