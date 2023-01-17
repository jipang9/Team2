package Team2.com.member.entity;

import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;


    @Builder
    public Member(String username, String password, MemberRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void changeRole(MemberRoleEnum role){
        this.role= role;
    }

    public void checkByMemberRole(String role, Member member)throws RuntimeException{
        if(member.getRole().equals(MemberRoleEnum.CUSTOMER)){
            if(role.equals("UP")) return;
            else throw new CustomException(ErrorCode.ERROR_DATA_BY_ROLE);
        }else{
            if(role.equals("UP")) throw new CustomException(ErrorCode.ERROR_DATA_BY_ROLE);
            else return;
        }

    }



}
