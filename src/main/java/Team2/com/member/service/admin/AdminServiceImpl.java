package Team2.com.member.service.admin;

import Team2.com.member.dto.AppliesResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.entity.Request;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.repository.RequestRepository;
import Team2.com.member.service.member.MemberService;
import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;

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
    @Transactional
    public void addRoles(Long id) {
        Request request = requestRepository.findById(id).get();
        Member member = memberRepository.findById(request.getId()).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        member.changeRole(MemberRoleEnum.SELLER);
        requestRepository.delete(request);
    }

    @Override
    public void deleteRoles(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        member.changeRole(MemberRoleEnum.CUSTOMER);
    }

    @Override
    public List<Request> getAppliesList() {
        List<Request> all = requestRepository.findAll();
        return all;
    }
}
