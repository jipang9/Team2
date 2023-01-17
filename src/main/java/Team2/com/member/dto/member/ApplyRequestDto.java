package Team2.com.member.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ApplyRequestDto {
    private final Long id;
    private final String status;
    private final Long user;

    public ApplyRequestDto(Long id, String status, Long user) {
        this.id = id;
        this.status = status;
        this.user = user;
    }
}
