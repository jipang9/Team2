package Team2.com.member.service;

import java.util.List;

public interface AdminService {

    List<MembersResponseDto> getMemberList();

    List<SellersResponseDto> getSellerList();

    void addRoles();

    void deleteRoles();

}
