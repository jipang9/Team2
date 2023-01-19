package Team2.com.member.entity;

import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Member extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int point;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;


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
