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
import Team2.com.order.service.OrderService;
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
    private final OrderService orderService;

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
        if (memberRepository.existsByEmail(email))
            throw new CustomException(DUPLICATED_USERNAME);
    }

    @Override
    public void checkByMemberPhoneNumber(String phoneNumber) {
        if (memberRepository.existsByPhoneNumber(phoneNumber))
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


    @Override // 권한 등록
    public void apply(ApplyRequestDto applyRequestDto, Member member) {
        checkMembersRequestExistException(member.getId());
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
    public InfoResponseDto getMyInfo(Member member) {
        InfoResponseDto info = new InfoResponseDto(member);
        return info;
    }


    @Override // 요청 취소 ( 완료 )
    public void cancelRequestFromMember(Long id) {
        checkMembersRequestNotExistException(id);
        requestRepository.deleteByUserId(id); // query에 트랜잭션 걸려있음
    }


    @Override // 회원탈퇴
    public void withdrawal(Long id) { // 넘어온 값 -> 사용자 pk
        checkMembersRequestExistException(id); // 요청이 있는지 없는지 확인
        orderService.checkOrder(id);
        // 아직 상품처리가 됐는지 안됐는지 -> 안됐으면 취소 예외 -> 됐으면 ㄱㅊ
    }

    @Override // 요청이 존재하지 않으면 예외 ( 완료 )
    public void checkMembersRequestNotExistException(Long id) {
        if (requestRepository.existsByUser(id)) { //  true & false
            return;
        } else
            throw new CustomException(THIS_REQUEST_IS_ALREADY);
    }

    @Override // 요청이 존재하면 예외 ( 완료 )
    public void checkMembersRequestExistException(Long id) {
        if (requestRepository.existsByUser(id)) { //  true & false
            throw new CustomException(REQUEST_IS_EXIST);
        } else
            return;
    }



}

