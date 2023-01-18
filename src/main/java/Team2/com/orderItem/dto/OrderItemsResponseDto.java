package Team2.com.orderItem.dto;

import lombok.Getter;

@Getter
public class OrderItemsResponseDto {
    private Long id;
    private String itemName;

    public OrderItemsResponseDto(Long id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }
}
