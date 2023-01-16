package Team2.com.member.dto.member;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String username;

    private String password;

    //private String email;
    private boolean admin = false;  //admin 인지 아닌지 확인
    private String adminToken = "";
}