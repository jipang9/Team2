package Team2.com.item.service;

import Team2.com.item.dto.ItemDto;
import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public List<ItemDto.ResponseItemDto> getItemAllList(PageRequest pageRequest) {

        List<Item> items = itemRepository.findAllByOrderByIdDesc(pageRequest);

        List<ItemDto.ResponseItemDto> itemDtos = new ArrayList<>();
        for(int i=0; i<items.size(); i++){
            itemDtos.add(new ItemDto.ResponseItemDto(items.get(i).getName(), items.get(i).getContent(),
                                                            items.get(i).getCount(), items.get(i).getPrice(), items.get(i).getMember().getUsername()));
        }
        return itemDtos;
    }

    //상품 조회
    @Transactional(readOnly = true)
    public ItemDto.ResponseItemDto getItem(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return new ItemDto.ResponseItemDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername());
    }
    //상품등록
    @Transactional
    public ItemDto.ResponseItemDto addItem(ItemDto.RequestItemDto requestItemDto, String sellerName) {

        //0. 임의로 member 1명 데이터 삽입함.
        //memberRepository.saveAndFlush(seller);

        //1. 로그인한 판매자 정보 가져오기
        Member member = memberRepository.findByUsernameAndAndRole(sellerName, MemberRoleEnum.SELLER).orElseThrow(()-> new IllegalArgumentException("요청하신 판매자가 정보가 존재하지 않습니다."));

        //2. 상품 등록
        Item item = itemRepository.saveAndFlush(new Item(requestItemDto.getItemName(), requestItemDto.getContent(), member, requestItemDto.getPrice(), requestItemDto.getCount()));

        //3. 등록 상품 반환
        return new ItemDto.ResponseItemDto(item.getName(), item.getContent(), item.getPrice(), item.getCount(), item.getMember().getUsername());
    }

    //제품 수정
    @Transactional
    public void modifyItem(Long itemId, ItemDto.RequestItemDto requestItemDto, String sellerName) {

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
