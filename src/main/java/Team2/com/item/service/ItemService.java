package Team2.com.item.service;

import Team2.com.item.dto.ItemDto;
import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.service.member.MemberService;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    //전체 상품 조회
    @Transactional(readOnly = true)
    public ItemDto.Result getItemAllList(int offset, int limit) {

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<Item> items = itemRepository.findAllByOrderByIdDesc(pageRequest);

        Page<ItemDto.Response> map = items.map(item -> new ItemDto.Response(item.getName(), item.getContent(), item.getPrice(),item.getCount(), item.getMember().getUsername()));

        List<ItemDto.Response> content = map.getContent();
        long totalCount = map.getTotalElements();

        ItemDto.Result result = new ItemDto.Result(offset, totalCount, content);

        return result;
    }

    //상품 조회
    @Transactional(readOnly = true)
    public ItemDto.Response getItem(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return new ItemDto.Response(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername());
    }

    //상품등록
    @Transactional
    public ItemDto.Response addItem(ItemDto.Request requestItemDto, String sellerName) {

        //1. 로그인한 판매자 정보 가져오기
        Member member = memberRepository.findByUsernameAndAndRole(sellerName, MemberRoleEnum.SELLER).orElseThrow(()-> new IllegalArgumentException("요청하신 판매자가 정보가 존재하지 않습니다."));

        //2. 상품 등록
        Item item = itemRepository.saveAndFlush(new Item(requestItemDto.getItemName(), requestItemDto.getContent(), member, requestItemDto.getPrice(), requestItemDto.getCount()));

        //3. 등록 상품 반환
        return new ItemDto.Response(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername());
    }

    //제품 수정
    @Transactional
    public void modifyItem(Long itemId, ItemDto.Request requestItemDto, String sellerName) {

        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));

        //2. 해당 상품의 판매자 인지 확인
        if(!item.getMember().getUsername().equals(sellerName)){
            throw new IllegalArgumentException("해당 상품의 판매자와 일치하지 않습니다.");
        }

        //3. 상품 수정
        item.update(requestItemDto.getItemName(), requestItemDto.getContent(), item.getMember(), requestItemDto.getPrice(), requestItemDto.getCount());
    }
    
    //제품 삭제
    @Transactional
    public void deleteItem(Long itemId, String sellerName){
        //1. 상품 조회
        Item item = itemRepository.findById(itemId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));

        //2. 해당 상품의 판매자 인지 확인
        if(!item.getMember().getUsername().equals(sellerName)){
            throw new IllegalArgumentException("해당 상품의 판매자와 일치하지 않습니다.");
        }
        //3. 상품 삭제
        itemRepository.deleteById(itemId);
    }
}
