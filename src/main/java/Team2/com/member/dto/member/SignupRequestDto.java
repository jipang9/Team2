package Team2.com.member.dto.member;

import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String username;

    private String password;

    //private String email;
    private boolean admin = false;  //admin 인지 아닌지 확인
    private String adminToken = "";

    public Member toEntity(String password, MemberRoleEnum role){
        return Member.builder()
                .username(this.getUsername())
                .password(password)
                .role(role).build();
    }
}