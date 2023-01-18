package Team2.com.member.service.member;

import Team2.com.member.dto.InfoResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.dto.member.InfoDto;
import Team2.com.member.dto.member.LoginRequestDto;
import Team2.com.member.dto.member.SignupRequestDto;
import Team2.com.member.entity.Member;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MemberService {

    // 회원부
    void signup(SignupRequestDto signupRequestDto); // 회원가입

    void login(LoginRequestDto loginRequestDto, HttpServletResponse response); // 로그인

    InfoResponseDto getMyInfo(Member member); // 내 정보 가지고 오기

    void checkByRequest(Long id); // 사용자 요청이 있는지 확인


    // 관리부
    List<MembersResponseDto> getMemberLists(); // 유저 listUp(페이징 x)
        List<SellersResponseDto> getSellerLists(); // 판매자 listUp ( 페이징 x )
    void apply(ApplyRequestDto applyRequestDto, Member member); // 사용자 요청(등업, 강등)
    List<SellersResponseDto> getSellerOne(String sellerId); // 판매자 한명 조회


    void checkByMemberDuplicated(String username); // 사용자 중복 확인

    void checkByMemberPhoneNumber(String phoneNumber); // 사용자 휴대폰 중복 확인


}
