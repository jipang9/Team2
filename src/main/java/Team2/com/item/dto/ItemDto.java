package Team2.com.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ItemDto {

    @Getter
    @NoArgsConstructor
    public static class RequestItemDto{

        private String itemName;

        private String content;

        private int price;

        private int count;

        public RequestItemDto(String itemName, String content, int price, int count){
            this.itemName = itemName;
            this.content = content;
            this.price = price;
            this.count = count;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ResponseItemDto{

        private String itemName;

        private String content;

        private int price;

        private int count;

        public ResponseItemDto(String itemName, String content, int price, int count){
            this.itemName = itemName;
            this.content = content;
            this.price = price;
            this.count = count;
        }
    }
}
