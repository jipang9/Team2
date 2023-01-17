package Team2.com.member.dto.admin;

import Team2.com.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SellersResponseDto {

    private final Long id;
    private final String username;
    private final String role;


    public SellersResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.role = member.getRole().toString();
    }


    public static SellersResponseDto of (Member member){
        return new SellersResponseDto(member);
    }

}
