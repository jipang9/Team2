package Team2.com.member.dto.admin;


import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class MembersResponseDto {


    private final Long id; // 사용자 식별자 값
    private final String username; // 사용자 아이디
    private final String role; // 사용자 role


    public MembersResponseDto(Member member){
        this.id=member.getId();
        this.username=member.getUsername();
        this.role=member.getRole().toString();
    }

    public static MembersResponseDto of (Member member){
        return new MembersResponseDto(member);
    }

}
