package Team2.com.member.dto.member;

import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @Size(min = 4,max = 12,message ="아이디는 4에서 12자 사이 입니다.")
    @Pattern(regexp = "[a-z0-9]*$",message = "아이디 형식이 일치하지 않습니다.")
    private String username;

    @Size(min = 8,max = 15,message ="비밀번호는 8에서 15자 사이 입니다.")
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()_=+|{};:,.<>/?]*$",message = "비밀번호 형식이 일치하지 않습니다.")
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