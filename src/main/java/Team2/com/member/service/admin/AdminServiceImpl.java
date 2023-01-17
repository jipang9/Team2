package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.service.member.MemberService;
import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Override
    public List<MembersResponseDto> getMemberList() {
        List<MembersResponseDto> memberList = memberService.getMemberLists();
        return memberList;
    }

    @Override
    public List<SellersResponseDto> getSellerList() {
        List<SellersResponseDto> sellerLists = memberService.getSellerLists();
        return sellerLists;
    }

    @Override
    public void addRoles(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        member.chanegRole(MemberRoleEnum.ADMIN);

    }

    @Override
    public void deleteRoles() {

    }
}
