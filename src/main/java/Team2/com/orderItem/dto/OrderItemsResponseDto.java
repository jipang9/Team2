package Team2.com.orderItem.dto;

import lombok.Getter;

@Getter
public class OrderItemsResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private int count;


    public OrderItemsResponseDto(Long id, String itemName, int price, int count) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }
}
