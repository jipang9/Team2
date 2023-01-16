package Team2.com.member.service;

import Team2.com.member.dto.MembersResponseDto;
import Team2.com.member.dto.SellersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MemberService memberService;

    @Override
    public List<MembersResponseDto> getMemberList() {
        List<MembersResponseDto> memberList = memberService.getMemberList();
        return memberList;
    }

    @Override
    public List<SellersResponseDto> getSellerList(String role) {
        List<SellersResponseDto> sellersList = memberService.getSellersList(role);
        return sellersList;
    }

    @Override
    public void addRoles() {

    }

    @Override
    public void deleteRoles() {

    }
}
