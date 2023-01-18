package Team2.com.member.dto.member;

import lombok.Getter;

@Getter
public class InfoDto {

    private String name;

    String authorities;

    public InfoDto(String name, String authorities) {
        this.name = name;
        this.authorities = authorities;
    }
}
