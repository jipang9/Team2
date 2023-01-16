package Team2.com.item.entity;

import Team2.com.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 속성들을 다 모아서보여주는 단축키, 어노테이션 한번에 import - auto import,  (code complation(컴플레이션))


    @Column(name="item_name")
    private String name;

    @Column(name="item_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name="item_price")
    private int price;

    @Column(name="item_count")
    private int count;

}
