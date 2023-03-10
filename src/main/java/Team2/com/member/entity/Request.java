package Team2.com.member.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Request extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_role")
    private String role;

    @Column(name = "member_status")
    private String status;

    @Column(name = "member_id", unique = true)
    private Long user;

    public Request(Long member, String role, String status) {
        this.user = member;
        this.role = role;
        this.status = status;
    }



}
