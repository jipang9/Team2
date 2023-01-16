package Team2.com.order.dto;

import Team2.com.item.entity.Item;
import Team2.com.orderItem.dto.OrderItemsDto;
import Team2.com.orderItem.entity.OrderItems;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    @Getter
    public static class RequestOrderDto{
        // private Item item;
        // private
    }

    @Getter
    public static class ResponseOrderDto{
        private Long id;
        private String username;
        private List<OrderItemsDto> orderItemsList = new ArrayList<>();


        public ResponseOrderDto(Long id, String username, List<OrderItems> orderItems) {
            this.id = id;
            this.username = username;
            for (OrderItems o : orderItems) {
                OrderItemsDto orderItemsDto = new OrderItemsDto(o.getId(), o.getItem().getName(), o.getItem().getPrice(), o.getItem().getCount());
                orderItemsList.add(orderItemsDto);
            }
        }
    }

}
