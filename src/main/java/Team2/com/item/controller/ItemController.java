package Team2.com.item.controller;


import Team2.com.item.dto.ItemRequestDto;
import Team2.com.item.dto.ItemResponseDto;
import Team2.com.item.dto.ResultResponseDto;
import Team2.com.item.service.ItemService;
import Team2.com.security.details.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "상점")
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/seller/product")
    @ApiOperation(value = "상품 등록")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus addItem(@Valid @RequestBody ItemRequestDto requestItemDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.addItem(requestItemDto, userDetails.getUsername());
        return HttpStatus.OK;
    }

    @GetMapping("/products")
    @ApiOperation(value = "모든 제품 조회")
    public ResponseEntity getItemAllList(@RequestParam int offset, @RequestParam int limit){
        ResultResponseDto itemAllList = itemService.getItemAllList(offset, limit);
        return new ResponseEntity(itemAllList, HttpStatus.OK);
    }


    @GetMapping("/seller/products")
    @ApiOperation(value = "판매자 제품 조회")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public ResponseEntity getSellerItemAllList(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResultResponseDto selleritemAllList = itemService.getSellerItemAllList(offset, limit, userDetails.getUsername());
        return new ResponseEntity(selleritemAllList, HttpStatus.OK);
    }

    @ApiOperation(value = "단일 제품 조회")
    @GetMapping("/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_CUSTOMER"})
    public ResponseEntity getItem(@PathVariable("id") Long itemId){
        ItemResponseDto item = itemService.getItem(itemId);
        return new ResponseEntity(item, HttpStatus.OK);
    }


    @ApiOperation(value = "상품 정보 수정")
    @PutMapping("/seller/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus modifyItem(@PathVariable("id") Long itemId, @RequestBody ItemRequestDto requestItemDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.modifyItem(itemId, requestItemDto, userDetails.getUsername());
        return HttpStatus.OK;
    }

    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/seller/product/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    public HttpStatus deleteItem(@PathVariable("id") Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.deleteItem(itemId, userDetails.getUsername());
        return HttpStatus.OK;
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ItemResponseDto>> searchItems(@RequestParam(value = "item")String item){
        List<ItemResponseDto> items = itemService.searchItems(item);
        return ResponseEntity.status(200).body(items);
    }

}
