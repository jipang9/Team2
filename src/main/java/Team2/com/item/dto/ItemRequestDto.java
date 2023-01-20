package Team2.com.item.dto;

import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(force = true)
public class ItemRequestDto {

    @NotNull
    private String itemName;
    private String content;
    @NotNull
    private int price;
    @NotNull
    private int count;

    public ItemRequestDto(String itemName, String content, int price, int count){
        this.itemName = itemName;
        this.content = content;
        this.price = price;
        this.count = count;
    }

    public void checkItem(String itemName, int price, int count){
        if(itemName.isEmpty()){
            throw new CustomException(ErrorCode.INVALID_ITEM_NAME);
        }
        if(price == 0){
            throw new CustomException(ErrorCode.INVALID_ITEM_PRICE);
        }
        if(count>100000){
            throw new CustomException(ErrorCode.INVALID_ITEM_COUNTOVER);
        }
        if(count == 0){
            throw new CustomException(ErrorCode.INVALID_ITEM_ZERO_COUNT);
        }
    }
}
