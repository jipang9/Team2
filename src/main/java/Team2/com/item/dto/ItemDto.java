package Team2.com.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ItemDto {

    @Getter
    @NoArgsConstructor(force = true)
    public static class Request{

        private String itemName;

        private String content;

        private int price;

        private int count;

        public Request(String itemName, String content, int price, int count){
            this.itemName = itemName;
            this.content = content;
            this.price = price;
            this.count = count;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response{

        private String itemName;

        private String content;

        private int price;

        private int count;

        private String sellerName;

        public Response(String itemName, String content, int price, int count, String sellerName){
            this.itemName = itemName;
            this.content = content;
            this.price = price;
            this.count = count;
            this.sellerName = sellerName;
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
