package Team2.com.member.service.admin;

import Team2.com.item.repository.ItemRepository;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.entity.Request;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.repository.RequestRepository;
import Team2.com.member.service.member.MemberService;
import Team2.com.order.repository.OrderRepository;
import Team2.com.order.service.OrderService;
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

import static Team2.com.security.exception.ErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final OrderService orderService;

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

    // 관점 1. 예를들어서 -> 1번 처리가 up인데, 만약에 down을 했다면? -> 물론 처리는 돼겠지만, 해당 데이터는 사라진다..
    // db를 나눠야 하나? 아니면 이를 처리하는 로직을 새로 만들어서 check 해야하나?

    // Q : 역할을 부여하고, 뺏는 이 기능이 -> 요청이 있어야만 기능을 활성화 할 것인가?
    @Override
    @Transactional
    public void addRoles(Long id) {
        Request request = checkByRoleApplies(id);
        if (request.getStatus().equals("UP")) { // 이 내용을 한 서비스단에서 처리할 지, 분리할지..
            Member member = memberRepository.findById(request.getUser()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
            member.changeRole(MemberRoleEnum.SELLER);
            memberRepository.save(member);
            requestRepository.delete(request);
        } else {
            throw new CustomException(ErrorCode.THIS_REQUEST_IS_NOT_UPDATEROLE);
        }
    }

    @Override
    public Request checkByRoleApplies(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.THIS_REQUEST_IS_ALREADY));
        return request;
    }


    @Override
    @Transactional
    public void deleteRoles(Long id) {
        Request request = checkByRoleApplies(id);
        if (request.getStatus().equals("DOWN")) {
            Member member = memberRepository.findById(request.getUser()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
            member.changeRole(MemberRoleEnum.CUSTOMER);
            requestRepository.delete(request);
        } else {
            throw new CustomException(ErrorCode.THIS_REQUEST_IS_NOT_UPDATEROLE);
        }

    }

    @Override
    public List<Request> getAppliesList() {
        List<Request> all = requestRepository.findAll();
        return all;
    }

    @Override // 느낌이 끊어올려고 count query가  발생하네 맞네.
    public List<MembersResponseDto> MemberListPaging(Pageable pageable, int page) {
        List<MembersResponseDto> resultList = new ArrayList<>();
        Page<Member> members = memberRepository.findAll(pageable.withPage(page));
        for (Member member : members) {
            MembersResponseDto data = MembersResponseDto.of(member);
            resultList.add(data);
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

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        memberRepository.delete(member);
    }

    @Override
    public void deleteMemberForce(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        // 회원탈퇴 로직 순서 ->
        // 1. 해당 유저의 요청이 있는지 없는지 판단하고, 만약에 요청이 있으면 요청이 있음을 알려준다.  -> 회원탈퇴 실패 ( 요청 철회 기능 만들어야함.)
        // 1-1. 요청이 없으면 다음 로직으로 이동  ->
//        memberService.cancelRequestFromAdmin(id);// 요청 있다 vs 없다. -> 있으면 삭제, 없으면 pass
        log.info("다음 로직으로 GOGO");

        // 2. 해당 유저의 상품 주문이 있는지 없는지 확인한다, -> 만약 유저 상품 주문이 있으면 -> 회원탈퇴 실패  ( 주문 철회 기능 필요함)
        // 2-1. 상품 주문이 없으면 다음 로직으로 이동 ->
//        orderService.cancelOrder(id); // 해당 유저 주문 있다 vs 없다.


        // 3. 회원을 삭제해버리면 된다.

//        itemRepository.deleteByMember(member.getId());
//        memberRepository.delete(member);


        // 회원탈퇴 관련한 고찰
        // 어드민이 강제로?  회원이 자발적으로?  혹은 둘 다 ?

    }


    // 해당 유저의 정보를 찾아온다 -> 해당 유저가 요청이 있는지 없는지 확인한다.
    @Override // 판매자에 의한 요청 취소
    public void cancelRequestFromAdmin(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new CustomException(NOT_FOUND_USER));
        memberService.checkMembersRequestExistException(member.getId());
        requestRepository.deleteByUserId(member.getId());
    }

}

