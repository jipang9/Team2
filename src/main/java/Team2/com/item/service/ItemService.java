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

    ResultResponseDto getItemAllList(int offset, int limit);

    ItemResponseDto getItem(Long itemId);

    ItemResponseDto addItem(ItemRequestDto requestItemDto, String sellerName);

    void modifyItem(Long itemId, ItemRequestDto requestItemDto, String sellerName);

    void deleteItem(Long itemId, String sellerName);
}
