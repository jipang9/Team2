package Team2.com.order.dto;

import Team2.com.item.entity.Item;
import Team2.com.orderItem.dto.OrderItemsDto;
import Team2.com.orderItem.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


public class OrderDto {

    @Getter
    public static class Request{
        private List<RequestItemDto> items;
    }

    @Getter
    @ToString(of = {"id", "count"})
    public static class RequestItemDto{
        private Long id;
        private int count;
    }


    @Getter
    public static class Response{
        private Long id;
        private String username;
        private List<OrderItemsDto.Response> orderItemsList = new ArrayList<>();


        public Response(Long id, String username, List<OrderItems> orderItems) {
            this.id = id;
            this.username = username;
            for (OrderItems o : orderItems) {
                OrderItemsDto.Response orderItemsDto = new OrderItemsDto.Response(o.getId(), o.getItem().getName());
                orderItemsList.add(orderItemsDto);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Result<T>{
        private int currentPage;
        private Long count;
        private T data;
    }
}
