package Team2.com.member.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

//모든 필드값을 파라미터로 받는 생성자 생성
@AllArgsConstructor

//파라미터가 없는 기본생성자를 생성
@NoArgsConstructor

//해당 클래스에 자동으로 빌더를 추가
@Builder
public class MsgResponseDto {

    private String msg;
    private int statusCode;

}
