package Team2.com.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private List<OrderRequestItemDto> items;

}

