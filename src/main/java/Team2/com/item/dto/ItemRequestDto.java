package Team2.com.item.dto;

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
}
