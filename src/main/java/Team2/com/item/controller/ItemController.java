package Team2.com.item.controller;


import Team2.com.item.dto.ItemRequestDto;
import Team2.com.item.dto.ItemResponseDto;
import Team2.com.item.dto.ResultResponseDto;
import Team2.com.item.service.ItemService;
import Team2.com.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class ItemController {

    private final ItemService itemService;

    //제품 등록
    @PostMapping("/product")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus addItem(@RequestBody ItemRequestDto requestItemDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.addItem(requestItemDto, userDetails.getUsername());
        return HttpStatus.OK;
    }

    // 모든 제품 조회
    @GetMapping("/products")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
    public ResponseEntity getItemAllList(@RequestParam int offset, @RequestParam int limit){
        ResultResponseDto itemAllList = itemService.getItemAllList(offset, limit);
        return new ResponseEntity(itemAllList, HttpStatus.OK);
    }

    //단일 제품 조회
    @GetMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
    public ResponseEntity getItem(@PathVariable("id") Long itemId){
        ItemResponseDto item = itemService.getItem(itemId);
        return new ResponseEntity(item, HttpStatus.OK);
    }


    // 상품 수정
    @PutMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus modifyItem(@PathVariable("id") Long itemId, @RequestBody ItemRequestDto requestItemDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.modifyItem(itemId, requestItemDto, userDetails.getUsername());
        return HttpStatus.OK;
    }


    //상품 삭제
    @DeleteMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus deleteItem(@PathVariable("id") Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.deleteItem(itemId, userDetails.getUsername());
        return HttpStatus.OK;
    }


}
