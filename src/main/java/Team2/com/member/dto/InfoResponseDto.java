package Team2.com.member.dto;

import lombok.Data;

@Data
public class InfoResponseDto {
    private String username;
    private String role;

    public InfoResponseDto(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
