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
        private List<OrderItemDto>
        // private Item item;
        // private
    }

    @Getter
    public static class ResponseOrderDto{
        private Long id;
        private String username;
        private List<OrderItemsDto.Response> orderItemsList = new ArrayList<>();


        public ResponseOrderDto(Long id, String username, List<OrderItems> orderItems) {
            this.id = id;
            this.username = username;
            for (OrderItems o : orderItems) {
                OrderItemsDto.Response orderItemsDto = new OrderItemsDto.Response(o.getId(), o.getItem().getName(), o.getItem().getPrice(), o.getItem().getCount());
                orderItemsList.add(orderItemsDto);
            }
        }
    }

}
