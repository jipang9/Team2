package Team2.com.order.dto;

import Team2.com.orderItem.dto.OrderItemsResponseDto;
import Team2.com.orderItem.entity.OrderItems;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Long id;
    private int totalPrice;
    private List<OrderItemsResponseDto> orderItemsList = new ArrayList<>();


    public OrderResponseDto(Long id, List<OrderItems> orderItems) {
        this.id = id;
        this.totalPrice = totalPrice(orderItems);
        for (OrderItems o : orderItems) {
            OrderItemsResponseDto orderItemsDto = new OrderItemsResponseDto(o.getItem().getId(), o.getItem().getName(), o.getItem().getPrice(), o.getCount());
            orderItemsList.add(orderItemsDto);
        }
    }

    public int totalPrice(List<OrderItems> orderItems){
        int sum = 0;
        for (OrderItems o : orderItems) {
            sum += o.getItem().getPrice() * o.getCount();
        }
        return sum;
    }
}
