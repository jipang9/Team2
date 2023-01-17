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
        private int price;
        private int count;

        public Response(Long id, String itemName, int price, int count) {
            this.id = id;
            this.itemName = itemName;
            this.price = price;
            this.count = count;
        }
    }

}
