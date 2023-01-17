package Team2.com.member.service.admin;

import Team2.com.member.dto.AppliesResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Request;

import java.util.List;

public interface AdminService {

    List<MembersResponseDto> getMemberList();

    List<SellersResponseDto> getSellerList();

    void addRoles(Long id);

    void deleteRoles(Long id);

    List<Request> getAppliesList();

}
