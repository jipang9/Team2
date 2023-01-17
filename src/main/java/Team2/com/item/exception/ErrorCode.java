package Team2.com.item.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_SELLER(BAD_REQUEST, "해당 상품의 판매자만 수정/삭제 할 수 있습니다."),

    //404 NOT_FOUND 잘못된 리소스 접근했을 경우
    ITEM_NOT_FOUND(NOT_FOUND,  "찾으시는 상품이 존재하지 않습니다."),
    SELLER_NOT_FOUND(NOT_FOUND,  "판매자 정보가 존재하지 않습니다."),

    //500 서버에서 발생했을 경우
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러입니다.");
    

    private final HttpStatus httpStatus;

    private final String mseesage;
}
