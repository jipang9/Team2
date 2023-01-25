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
    private final String email; // 사용자 아이디
    private final String role; // 사용자 role
    private final int point; // 사용자 포인트
    private final String phoneNumber; // 사용자 핸드폰 번호

    public MembersResponseDto(Long id, String email, String role, String phoneNumber, int point ){
            this.id=id;
            this.email=email;
            this.role=role;
            this.phoneNumber=phoneNumber;
            this.point = point;
    }

    public static MembersResponseDto of (Member member){
        return new MembersResponseDto(member.getId(), member.getEmail(), member.getRole().toString(), member.getPhoneNumber(), member.getPoint());
    }

}
