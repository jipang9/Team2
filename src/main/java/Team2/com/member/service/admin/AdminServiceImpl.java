package Team2.com.member.service.admin;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.entity.Request;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.repository.RequestRepository;
import Team2.com.member.service.member.MemberServiceImpl;
import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;

//    @Override
//    public List<MembersResponseDto> getMemberList() {
//        List<MembersResponseDto> memberList = memberService.getMemberLists();
//        return memberList;
//    }

//    @Override
//    public List<SellersResponseDto> getSellerList() {
//        List<SellersResponseDto> sellerLists = memberService.getSellerLists();
//        return sellerLists;
//    }

    @Override
    @Transactional
    // 여기 예외처리 해야겠네
    public void addRoles(Long id) {
        Request request = requestRepository.findById(id).get();
        Member member = memberRepository.findById(request.getUser()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        member.changeRole(MemberRoleEnum.SELLER);
        memberRepository.save(member);
        requestRepository.delete(request);
    }

    // 요청이 있어야지만 역할을 줬다 뺏을 수 있나? -> 한번 생각해볼 것.
    @Override
    public void deleteRoles(Long id) {
        Request request = requestRepository.findById(id).get();
        Member member = memberRepository.findById(request.getUser()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        member.changeRole(MemberRoleEnum.CUSTOMER);
        requestRepository.delete(request);
    }

    @Override
    public List<Request> getAppliesList() {
        List<Request> all = requestRepository.findAll();// 중요한 로직이 아닌데도 노출하면 안 되는건가?
        return all;
    }

    @Override
    public List<MembersResponseDto> MemberListPaging(Pageable pageable, int page) {
        List<MembersResponseDto> resultList = new ArrayList<>();
        Page<Member> members = memberRepository.findAll(pageable.withPage(page));
        for (Member member : members) {
            MembersResponseDto membersResponseDto = new MembersResponseDto(member);
            resultList.add(membersResponseDto);
        }
        return resultList;
    }

    @Override
    public List<SellersResponseDto> SellerListPaging(Pageable pageable, int page) {
        List<SellersResponseDto> resultList = new ArrayList<>();
        List<Member> sellers = memberRepository.findAllBySellersPaging(pageable.withPage(page));
        for (Member seller : sellers) {
            SellersResponseDto sellersResponseDto = new SellersResponseDto(seller);
            resultList.add(sellersResponseDto);
        }
        return resultList;
    }

}
