package Team2.com.item.dto;

import Team2.com.item.entity.Item;
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
    public ItemResponseDto(Item item){
        this.itemName = item.getName();
        this.content = item.getContent();
        this.price = item.getPrice();
        this.count = item.getCount();
        this.sellerName = item.getMember().getName();

    }
}
