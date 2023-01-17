package Team2.com.member.service.admin;

import Team2.com.member.dto.AppliesResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Request;

import java.util.List;

public interface AdminService {

    List<MembersResponseDto> getMemberList(); // 유저 전체 조회

    List<SellersResponseDto> getSellerList(); // 판매자 전체 조회

    void addRoles(Long id); // 권한 부여
    //고민 1-> 권한 부여 혹은 삭제 기능을 한 서비스 로직에서 처리가 가능할 것 같은데, 어떻게 하는게 좋을지

    void deleteRoles(Long id); // 권한 삭제

    List<Request> getAppliesList(); // 요청 list 이거 dto로 고쳐야 할 것 같은데

}
