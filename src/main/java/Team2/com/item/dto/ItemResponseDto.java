package Team2.com.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ItemResponseDto {

    private String itemName;

    private String content;

    private int price;

    private int count;

    private String sellerName;

    public ItemResponseDto(String itemName, String content, int price, int count, String sellerName){
        this.itemName = itemName;
        this.content = content;
        this.price = price;
        this.count = count;
        this.sellerName = sellerName;
    }
}
