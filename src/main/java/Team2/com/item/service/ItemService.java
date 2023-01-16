package Team2.com.item.service;

import Team2.com.item.dto.ItemDto;
import Team2.com.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    //상품등록 -> 확인해야할 부분 -> 1. 판매자 정보 가져오기 ->
    @Transactional
    public void addItem(ItemDto.RequestItemDto requestItemDto, String ) {

    }
}
