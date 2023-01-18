package Team2.com.order.dto;

import Team2.com.orderItem.dto.OrderItemsResponseDto;
import Team2.com.orderItem.entity.OrderItems;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Long id;
    private String username;
    private List<OrderItemsResponseDto> orderItemsList = new ArrayList<>();


    public OrderResponseDto(Long id, String username, List<OrderItems> orderItems) {
        this.id = id;
        this.username = username;
        for (OrderItems o : orderItems) {
            OrderItemsResponseDto orderItemsDto = new OrderItemsResponseDto(o.getId(), o.getItem().getName());
            orderItemsList.add(orderItemsDto);
        }
    }
}
