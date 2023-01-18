package Team2.com.item.controller;


import Team2.com.item.dto.ItemDto;
import Team2.com.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
public class ItemController {

    private final ItemService itemService;

    //모든 제품 조회
    @GetMapping("/products")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getItemAllList(@RequestParam("page") Integer page){
        PageRequest pageRequest = PageRequest.of(page,10);
        List<ItemDto.ResponseItemDto> itemAllList = itemService.getItemAllList(pageRequest);
        if(itemAllList.isEmpty()){
            return new ResponseEntity("등록된 상품이 없습니다.", HttpStatus.OK);
        }
        return new ResponseEntity(itemAllList, HttpStatus.OK);
    }

    //하나의 제품 조회
    @GetMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getItem(@PathVariable("id") Long itemId){
        ItemDto.ResponseItemDto item = itemService.getItem(itemId);
        if(item==null){
            return new ResponseEntity("제품이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }


    //제품 등록
    @PostMapping("/product")
    public ResponseEntity addItem(@RequestBody ItemDto.Request requestItemDto, @AuthenticationPrincipal UserDetails userDetails){
        itemService.addItem(requestItemDto, userDetails.getUsername());
        return new ResponseEntity("제품등록이 완료되었습니다.", HttpStatus.OK);
    }

    //제품 수정
    @PutMapping("/product/{id}")
    public ResponseEntity modifyItem(@PathVariable("id") Long itemId, @RequestBody ItemDto.Request requestItemDto, @AuthenticationPrincipal UserDetails userDetails){
        itemService.modifyItem(itemId, requestItemDto, userDetails.getUsername());
        return new ResponseEntity("제품수정이 완료되었습니다.", HttpStatus.OK);
    }


    //제품 삭제
    @DeleteMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity deleteItem(@PathVariable("id") Long itemId, @AuthenticationPrincipal UserDetails userDetails){
        itemService.deleteItem(itemId, userDetails.getUsername());
        return new ResponseEntity("제품삭제가 완료되었습니다.", HttpStatus.OK);
    }
}
