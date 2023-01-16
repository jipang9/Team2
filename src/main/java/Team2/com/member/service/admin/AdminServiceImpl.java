package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AMemberService AMemberService;

    @Override
    public List<MembersResponseDto> getMemberList() {
        List<MembersResponseDto> memberList = AMemberService.getMemberList();
        return memberList;
    }

    @Override
    public List<SellersResponseDto> getSellerList(String role) {
        List<SellersResponseDto> sellersList = AMemberService.getSellersList(role);
        return sellersList;
    }

    @Override
    public void addRoles() {

    }

    @Override
    public void deleteRoles() {

    }
}
