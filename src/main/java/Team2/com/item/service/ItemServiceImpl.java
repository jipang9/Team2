package Team2.com.item.service;

import Team2.com.item.dto.ItemRequestDto;
import Team2.com.item.dto.ItemResponseDto;
import Team2.com.item.dto.ResultResponseDto;
import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.repository.MemberRepository;
import Team2.com.orderItem.repository.OrderItemsRepository;
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
    private final OrderItemsRepository orderItemsRepository;

    //상품등록
    @Transactional
    @Override
    public ItemResponseDto addItem(ItemRequestDto requestItemDto, String sellerName) {
        if(requestItemDto.getItemName().isBlank()){
            throw new CustomException(INVALID_ITEM_NAME);
        }
        if(requestItemDto.getPrice() == 0){
            throw new CustomException(INVALID_ITEM_PRICE);
        }
        if(requestItemDto.getCount() == 0){
            throw new CustomException(INVALID_ITEM_ZERO_COUNT);
        }
        Member member = memberRepository.findByNameAndAndRole(sellerName, MemberRoleEnum.SELLER).orElseThrow(() -> new CustomException(NOT_FOUND_SELLER));
        Item checkItem = itemRepository.findByName(requestItemDto.getItemName());
        if (checkItem != null) {
            throw new CustomException(DUPLICATED_ITEM);
        }
        Item addItem = itemRepository.saveAndFlush(new Item(requestItemDto.getItemName(), requestItemDto.getContent(), member, requestItemDto.getPrice(), requestItemDto.getCount()));
        return new ItemResponseDto(addItem.getName(), addItem.getContent(), addItem.getPrice(), addItem.getCount(), addItem.getMember().getName());
    }


    //전체 상품 조회
    @Transactional(readOnly = true)
    @Override
    public ResultResponseDto getItemAllList(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<Item> items = itemRepository.findAllByOrderByIdDesc(pageRequest);
        Page<ItemResponseDto> map = items.map(item -> new ItemResponseDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getName()));
        List<ItemResponseDto> content = map.getContent();
        long totalCount = map.getTotalElements();
        ResultResponseDto result = new ResultResponseDto(offset, totalCount, content);
        return result;
    }

    //상품 조회
    @Transactional(readOnly = true)
    @Override
    public ItemResponseDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
        return new ItemResponseDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getName());
    }


    //상품 수정
    @Transactional
    @Override
    public void modifyItem(Long itemId, ItemRequestDto requestItemDto, String sellerName) {
        if(requestItemDto.getItemName().isBlank()){
            throw new CustomException(INVALID_ITEM_NAME);
        }
        if(requestItemDto.getPrice() == 0){
            throw new CustomException(INVALID_ITEM_PRICE);
        }
        if(requestItemDto.getCount() == 0){
            throw new CustomException(INVALID_ITEM_ZERO_COUNT);
        }
        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
        //2. 해당 상품의 판매자 인지 확인
        if (!item.getMember().getName().equals(sellerName)) {
            //0. 상품변경 전 주문 내역이 있는지 확인
            int itemOrderCount = orderItemsRepository.countByItem_Id(itemId);

            if (itemOrderCount != 0) {
                throw new CustomException(INVALID_ITEM_STATUS);
            }

            //2. 해당 상품의 판매자 인지 확인
            if (!item.getMember().getName().equals(sellerName)) {
                throw new CustomException(NOT_FOUND_SELLER);}
            //3. 상품 수정
            item.update(requestItemDto.getItemName(), requestItemDto.getContent(), item.getMember(), requestItemDto.getPrice(), requestItemDto.getCount());
        }
    }

    //상품 삭제
    @Transactional
    @Override
    public void deleteItem(Long itemId, String sellerName) {
        //0. 상품변경 전 주문 내역이 있는지 확인
        int itemOrderCount = orderItemsRepository.countByItem_Id(itemId);
        if (itemOrderCount != 0) {
            throw new CustomException(INVALID_ITEM_STATUS);
        }
        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
        //2. 해당 상품의 판매자 인지 확인
        if (!item.getMember().getName().equals(sellerName)) {
            if (!item.getMember().getName().equals(sellerName)) {
                throw new CustomException(NOT_FOUND_SELLER);}
            //3. 상품 삭제
            itemRepository.deleteById(itemId);
        }
    }


}
