package Team2.com.member.service.member;

import Team2.com.member.dto.InfoResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.dto.member.LoginRequestDto;
import Team2.com.member.dto.member.SignupRequestDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.entity.Request;
import Team2.com.member.entity.Status;
import Team2.com.member.repository.MemberRepository;
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
import java.util.List;
import java.util.Optional;

import static Team2.com.security.exception.ErrorCode.*;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        checkByMemberDuplicated(signupRequestDto.getEmail());
        checkByMemberPhoneNumber(signupRequestDto.getPhoneNumber());
        MemberRoleEnum role = MemberRoleEnum.CUSTOMER;
        //이 부분 한번 고민해봤으면 좋겠음.
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(INVALID_TOKEN);
            }
            role = MemberRoleEnum.ADMIN;
        }
        Member member = signupRequestDto.toEntity(passwordEncoder.encode(signupRequestDto.getPassword()), role);
        memberRepository.save(member);
    }

    @Override
    public void checkByMemberDuplicated(String email) {
        if(memberRepository.existsByEmail(email))
            throw new CustomException(DUPLICATED_USERNAME);}

    @Override
    public void checkByMemberPhoneNumber(String phoneNumber) {
        if(memberRepository.existsByPhoneNumber(phoneNumber))
            throw new CustomException(DUPLICATED_PHONENUMBER);
    }

    @Override
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new CustomException(NOT_MATCH_INFORMATION);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getEmail(), member.getRole()));
    }

    @Override
    public List<MembersResponseDto> getMemberLists() {
        return memberRepository.findAllByMembers();
    }

    @Override
    public List<SellersResponseDto> getSellerLists() {
        return memberRepository.findAllBySellers();
    }


    @Override
    public void apply(ApplyRequestDto applyRequestDto, Member member) {
        checkByRequest(member.getId());
        member.checkByMemberRole(applyRequestDto.getStatus(), member);
        if (member.getRole().equals(MemberRoleEnum.CUSTOMER)) {
            Request request = new Request(member.getId(), member.getRole().toString(), Status.UP.toString());
            requestRepository.save(request);
        } else {
            Request request = new Request(member.getId(), member.getRole().toString(), Status.DOWN.toString());
            requestRepository.save(request);
        }
    }

    @Override
    public List<SellersResponseDto> getSellerOne(String sellerId) {
        return memberRepository.findBySellerId(Long.valueOf(sellerId));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkByRequest(Long id) {
        Optional<Request> check = requestRepository.findByMember(id);
        if (check.isEmpty() == true) {
            return;
        } else
            throw new CustomException(MEMBER_Already_REQUEST);
    }

    @Override
    @Transactional(readOnly = true)
    public InfoResponseDto getMyInfo(Member member) {
        InfoResponseDto info = new InfoResponseDto(member);
        return info;
    }

}
