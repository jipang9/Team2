package Team2.com.member.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ApplyRequestDto {
    private final String status;

    public ApplyRequestDto(String status) {
        this.status = status;
    }
}
