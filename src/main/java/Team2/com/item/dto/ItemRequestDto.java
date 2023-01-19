package Team2.com.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ItemRequestDto {

    private String itemName;

    private String content;

    private int price;

    private int count;

    public ItemRequestDto(String itemName, String content, int price, int count){
        this.itemName = itemName;
        this.content = content;
        this.price = price;
        this.count = count;
    }
}
