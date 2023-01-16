package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AMemberServiceImpl implements AMemberService {

    private final MemberRepository memberRepository;

    @Override // 사용자 가지고 오기
    public List<MembersResponseDto> getMemberList() {
        List<MembersResponseDto> MemberList = memberRepository.findAllByMembers();
        return MemberList;
    }

    @Override // 판매자 가지고 오기
    public List<SellersResponseDto> getSellersList(String role) {
        List<SellersResponseDto> SellerList = memberRepository.findAllBySellers(role);
        return SellerList;
    }
}
