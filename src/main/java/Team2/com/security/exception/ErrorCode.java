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
    INVALID_ITEM_PRICE(HttpStatus.BAD_REQUEST, "상품 가격은 1원 이상이어야 합니다."),
    INVALID_ITEM_ZERO_COUNT(HttpStatus.BAD_REQUEST, "상품 재고는 1개 이상이어야 합니다."),
    INVALID_ORDER_COUNT(HttpStatus.BAD_REQUEST, "주문 수량은 1개 이상이어야 합니다."),
    INVALID_SELLER_ITEM(HttpStatus.BAD_REQUEST, "판매자가 일치하지 않습니다"),
    INVALID_ITEM_STATUS(HttpStatus.BAD_REQUEST, "현재 주문된 상품이기에 수정/삭제가 불가합니다."),
    INVALID_ITEM_NAME(HttpStatus.BAD_REQUEST, "상품명을 입력해주세요."),
    INVALID_ITEM_COUNTOVER(HttpStatus.BAD_REQUEST, "상품 개수를 100,000개 이하로 등록해주세요."),
    DATA_ERROR(HttpStatus.BAD_REQUEST, "수량 및 가격, 상품명을 확인해주세요"),


    THIS_REQUEST_IS_NOT_UPDATEROLE(HttpStatus.BAD_REQUEST, " 잘못된 요청처리입니다. 요청을 확인해주세요 "),

    REQUEST_NOT_EXIST(HttpStatus.BAD_REQUEST," 해당 유저는 요청이 없습니다.  "),

    REQUEST_IS_EXIST(HttpStatus.BAD_REQUEST, " 해당 유저의 진행중인 요청이 있습니다. 요청 처리 후 다시 시도해주세요"),



    // 409 ->  CONFLICT : 중복 데이터 (ex. 이미 중복된 값)
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 사용중인 아이디입니다. 다시 확인하세요 "),
    DUPLICATED_PHONENUMBER(HttpStatus.CONFLICT, "이미 사용중인 휴대폰 번호 입니다. 다시 확인하세요 "),
    DUPLICATED_ITEM(HttpStatus.CONFLICT, "이미 존재하는 상품입니다"),

    MEMBER_REQUEST_DUPLICATED(HttpStatus.CONFLICT, "이미 요청 처리된 회원입니다."),

    ALREADY_ORDER(HttpStatus.CONFLICT, "이미 처리된 주문입니다."),



    // 404 ->  NOT _ FOUND : 잘못된 리소스 접근 (ex. 존재하지 않는 값)
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    NOT_FOUND_SELLER(HttpStatus.BAD_REQUEST, "판매자가 일치하지 않습니다."),
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST, "상품 정보를 찾을 수 없습니다."),
    NOT_EXIST_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리가 존재하지 않습니다."),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "댓글을 찾을 수 없습니다."),
    UNABLE_TO_FULLFILL_REQUEST(HttpStatus.BAD_REQUEST, " 요청을 수행할 수 없습니다"),
    NOT_FOUND_ORDERNUMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 주문번호 입니다."),

    THIS_REQUEST_IS_ALREADY(HttpStatus.BAD_REQUEST, " 이미 처리된 요청 혹은 없는 요청입니다."),

    NOT_EXIST_ORDERS(HttpStatus.BAD_REQUEST, " 주문 내역이 없습니다. "),

    // 401 -> 잘못된 인증 및 인가 정보
    ERROR_DATA_BY_ROLE(HttpStatus.UNAUTHORIZED, "잘못된 권한 요청 정보입니다. 다시 확인해주세요"),
    AUTHORIZATION(HttpStatus.UNAUTHORIZED, "작성자만 수정/삭제할 수 있습니다."),
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");
    

    private final HttpStatus httpStatus;
    private final String message;
}
