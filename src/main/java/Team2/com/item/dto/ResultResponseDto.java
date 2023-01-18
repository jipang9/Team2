package Team2.com.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponseDto<T> {

    private int currentPage;
    private Long count;
    private T data;
}
