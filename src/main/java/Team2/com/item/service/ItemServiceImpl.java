package Team2.com.item.service;

import Team2.com.item.dto.ItemRequestDto;
import Team2.com.item.dto.ItemResponseDto;
import Team2.com.item.dto.ResultResponseDto;
import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.repository.MemberRepository;
import Team2.com.order.repository.OrderRepository;
import Team2.com.security.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Team2.com.security.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    //전체 상품 조회
    @Transactional(readOnly = true)
    public ResultResponseDto getItemAllList(int offset, int limit) {

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<Item> items = itemRepository.findAllByOrderByIdDesc(pageRequest);

        Page<ItemResponseDto> map = items.map(item -> new ItemResponseDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername()));

        List<ItemResponseDto> content = map.getContent();
        long totalCount = map.getTotalElements();

        ResultResponseDto result = new ResultResponseDto(offset, totalCount, content);

        return result;
    }

    //상품 조회
    @Transactional(readOnly = true)
    public ItemResponseDto getItem(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));

        return new ItemResponseDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername());
    }

    //상품등록
    @Transactional
    public ItemResponseDto addItem(ItemRequestDto requestItemDto, String sellerName) {

        //1. 판매자 정보 가져오기
        Member member = memberRepository.findByUsernameAndAndRole(sellerName, MemberRoleEnum.SELLER).orElseThrow(() -> new CustomException(NOT_FOUND_SELLER));

        //2-1. 기존에 등록된 상품있는지 체크
        Item checkItem = itemRepository.findByName(requestItemDto.getItemName());

        //2-2. 기존 등록된 상품이 있을 경우
        if (checkItem != null) {
            //2-3. 기 등록된 상품의 판매자와 현재 로그인한 판매자 이름이 같을 경우 -> 하나의 판매자가 같은 상품 등록 요청을 한 것이기 때문에 exception처리
            if(checkItem.getMember().getUsername().equals(sellerName)){
                throw new CustomException(DUPLICATED_ITEM);
            }
        }

        //2-2. 상품 등록
        Item additem = itemRepository.saveAndFlush(new Item(requestItemDto.getItemName(), requestItemDto.getContent(), member, requestItemDto.getPrice(), requestItemDto.getCount()));

        //3. 등록 상품 반환
        return new ItemResponseDto(additem.getName(), additem.getContent(), additem.getPrice(), additem.getCount(), additem.getMember().getUsername());
    }

    //상품 수정
    @Transactional
    public void modifyItem(Long itemId, ItemRequestDto requestItemDto, String sellerName) {

        //0. 상품변경 전 주문된 내역이 있는지 확인


        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));

        //2. 해당 상품의 판매자 인지 확인
        if (!item.getMember().getUsername().equals(sellerName)) {
            throw new CustomException(NOT_FOUND_SELLER);
        }

        //3. 상품 수정
        item.update(requestItemDto.getItemName(), requestItemDto.getContent(), item.getMember(), requestItemDto.getPrice(), requestItemDto.getCount());
    }

    //상품 삭제
    @Transactional
    public void deleteItem(Long itemId, String sellerName) {
        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));

        //2. 해당 상품의 판매자 인지 확인
        if (!item.getMember().getUsername().equals(sellerName)) {
            throw new CustomException(NOT_FOUND_SELLER);
        }
        //3. 상품 삭제
        itemRepository.deleteById(itemId);
    }
}