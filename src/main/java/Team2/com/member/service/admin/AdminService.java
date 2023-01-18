package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.entity.Request;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

//    List<MembersResponseDto> getMemberList(); // 유저 전체 조회

//    List<SellersResponseDto> getSellerList(); // 판매자 전체 조회

    void addRoles(Long id); // 권한 부여

    void deleteRoles(Long id); // 권한 삭제

    List<Request> getAppliesList(); // 요청 list 이거 dto로 고쳐야 할 것 같은데

    List<MembersResponseDto> MemberListPaging(Pageable pageable, int page); // 페이징 - 사용자 조회
    List<SellersResponseDto> SellerListPaging(Pageable pageable, int page);  // 페이징 - 판매자 조회
}
