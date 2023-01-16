package Team2.com.item.controller;


import Team2.com.item.dto.ItemDto;
import Team2.com.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class ItemController {

    private final ItemService itemService;

    //모든 제품 조회
    @GetMapping("/products")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity ItemAllList(){

    }

    //하나의 제품 조회
    @GetMapping("/product/{id}")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity oneItem(@PathVariable Long itemId){

    }


    //제품 등록
    @PostMapping("/product")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity addItem(@RequestBody ItemDto.RequestItemDto requestItemDto){
        itemService.addItem(requestItemDto);
        return new ResponseEntity("제품등록이 완료되었습니다.", HttpStatus.OK);
    }

    //제품 수정
    @PutMapping("/product/{id}")
    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity modifyItem(@PathVariable Long itemId, @RequestBody ItemDto.RequestItemDto requestItemDto){

    }


    //제품 삭제
    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){

    }

}
