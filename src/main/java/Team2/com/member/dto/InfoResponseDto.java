package Team2.com.member.dto;

import Team2.com.member.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InfoResponseDto {

    private String email;
    private String role;
    private String phoneNumber;
    private String name;
    private LocalDateTime createDate;
    private int point;

        public InfoResponseDto(Member member) {
        this.email = member.getEmail();
        this.role = member.getRole().toString();
        this.phoneNumber = member.getPhoneNumber();
        this.name = member.getName();
        this.createDate = member.getCreateDate();
        this.point = member.getPoint();
    }


}
