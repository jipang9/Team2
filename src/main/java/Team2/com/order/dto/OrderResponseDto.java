package Team2.com.order.dto;

import Team2.com.orderItem.dto.OrderItemsResponseDto;
import Team2.com.orderItem.entity.OrderItems;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Long id;
    private List<OrderItemsResponseDto> orderItemsList = new ArrayList<>();

    public OrderResponseDto(Long id, List<OrderItems> orderItems) {
        this.id = id;
        for (OrderItems o : orderItems) {
            OrderItemsResponseDto orderItemsDto = new OrderItemsResponseDto(o.getItem().getId(), o.getItem().getName());
            orderItemsList.add(orderItemsDto);
        }
    }

}
