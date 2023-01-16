package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;

import java.util.List;

public interface AMemberService {

    List<MembersResponseDto> getMemberList();

    List<SellersResponseDto> getSellersList(String role);

}
