package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;

import java.util.List;

public interface AdminService {

    List<MembersResponseDto> getMemberList();

    List<SellersResponseDto> getSellerList();

    void addRoles(Long id);

    void deleteRoles();

}
