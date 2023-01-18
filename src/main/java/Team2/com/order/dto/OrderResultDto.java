package Team2.com.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResultDto <T>{
    private int currentPage;
    private Long count;
    private T data;
}

