package Team2.com.orderItem.dto;

import lombok.Getter;


public class OrderItemsDto {
    @Getter
    public static class Request{

    }
    @Getter
    public static class Response{
        private Long id;
        private String itemName;

        public Response(Long id, String itemName) {
            this.id = id;
            this.itemName = itemName;
        }
    }

}
