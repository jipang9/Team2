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
    private final String email;
    private final String role;
    private final String name;

    public SellersResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.role = member.getRole().toString();
        this.name = member.getName();
    }


}
