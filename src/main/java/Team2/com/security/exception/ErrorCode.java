package Team2.com.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 에러코드를 사용하실 때, 해당 코드에 맞춰서  응답을 보내는게 좋을듯 싶습니다. (김지환- 2023.01.18)

    // 500 -> INTERNAL SERVER ERROR : 서버에러

    // 400 ->  BAD _ REQUEST : 잘못된 요청 (ex. 파라미터 값을 확인해주세요 )
    NOT_MATCH_INFORMATION(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다."),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST, "username과 password의 형식이 올바르지 않습니다."),
    INVALID_ITEM_COUNT(HttpStatus.BAD_REQUEST, "재고보다 많은 수량을 입력하였습니다."),

    // 409 ->  CONFLICT : 중복 데이터 (ex. 이미 중복된 값)
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "중복된 username 입니다"),

    DUPLICATED_ITEM(HttpStatus.CONFLICT, "이미 존재하는 상품입니다"),
    MEMBER_Already_REQUEST(HttpStatus.CONFLICT, "이미 요청된 회원입니다."),

    // 404 ->  NOT _ FOUND : 잘못된 리소스 접근 (ex. 존재하지 않는 값)
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    NOT_FOUND_SELLER(HttpStatus.BAD_REQUEST, "판매자 정보를 찾을 수 없습니다."),
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST, "상품 정보를 찾을 수 없습니다."),
    NOT_EXIST_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리가 존재하지 않습니다."),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "댓글을 찾을 수 없습니다."),
    UNABLE_TO_FULLFILL_REQUEST(HttpStatus.BAD_REQUEST, " 요청을 수행할 수 없습니다"),
    NOT_FOUND_ORDERNUMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 주문번호 입니다."),

    // 401 -> 잘못된 인증 및 인가 정보
    ERROR_DATA_BY_ROLE(HttpStatus.UNAUTHORIZED, "잘못된 권한 요청 정보입니다. 다시 확인해주세요"),
    AUTHORIZATION(HttpStatus.UNAUTHORIZED, "작성자만 수정/삭제할 수 있습니다."),
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
