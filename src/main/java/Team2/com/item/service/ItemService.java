package Team2.com.item.service;

import Team2.com.item.dto.ItemRequestDto;
import Team2.com.item.dto.ItemResponseDto;
import Team2.com.item.dto.ResultResponseDto;
import Team2.com.security.details.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ItemService {


    ItemResponseDto addItem(ItemRequestDto requestItemDto, String sellerName); // 상품 등록

    void modifyItem(Long itemId, ItemRequestDto requestItemDto, String sellerName); // 상품 수정

    void deleteItem(Long itemId, String sellerName); //상품 삭제

    ResultResponseDto getItemAllList(int offset, int limit); // 전체 상품 리스트 출력
    ResultResponseDto getSellerItemAllList(int offset, int limit, String sellerName);   //판매자 전체 상품 조회
    ItemResponseDto getItem(Long itemId); // 상품 단일 조회 ->
}