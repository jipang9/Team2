package Team2.com.member.dto;


import Team2.com.security.MemberRoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MembersResponseDto {
// 고객 목록 어떤 데이터 줄까?? -> 그냥 고객 목록만 주면 되니까... 음.. .필요한 데이터만 준다고 생각해보자

    private final String nickname;
    private final MemberRoleEnum role;

    public MembersResponseDto(String nickname, MemberRoleEnum role) {
        this.nickname = nickname;
        this.role = role;
    }

}
