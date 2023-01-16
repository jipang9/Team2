package Team2.com.item.controller;


import Team2.com.item.dto.ItemDto;
import Team2.com.item.service.ItemService;
import Team2.com.member.entity.Member;
import lombok.RequiredArgsConstructor;
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
public class ItemController {

    private final ItemService itemService;
    private static final Member member = new Member("sein");

    //모든 제품 조회
    @GetMapping("/products")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getItemAllList(){
        List<ItemDto.ResponseItemDto> itemAllList = itemService.getItemAllList();
        if(itemAllList.isEmpty()){
            return new ResponseEntity("등록된 상품이 없습니다.", HttpStatus.OK);
        }
        return new ResponseEntity(itemAllList, HttpStatus.OK);
    }

    //하나의 제품 조회
    @GetMapping("/product/{id}")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getItem(@PathVariable Long itemId){
        ItemDto.ResponseItemDto item = itemService.getItem(itemId);
        return new ResponseEntity(item, HttpStatus.OK);
    }


    //제품 등록
    @PostMapping("/product")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity addItem(@RequestBody ItemDto.RequestItemDto requestItemDto){
        itemService.addItem(requestItemDto, member);
        return new ResponseEntity("제품등록이 완료되었습니다.", HttpStatus.OK);
    }

    //제품 수정
    @PutMapping("/product/{id}")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity modifyItem(@PathVariable Long itemId, @RequestBody ItemDto.RequestItemDto requestItemDto){
        itemService.modifyItem(itemId, requestItemDto, member);
        return new ResponseEntity("제품수정이 완료되었습니다.", HttpStatus.OK);
    }


    //제품 삭제
    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId, member);
        return new ResponseEntity("제품삭제가 완료되었습니다.", HttpStatus.OK);
    }
}