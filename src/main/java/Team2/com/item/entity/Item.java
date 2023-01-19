package Team2.com.item.entity;

import Team2.com.member.entity.Member;
import Team2.com.security.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static Team2.com.security.exception.ErrorCode.INVALID_ITEM_COUNT;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_name")
    private String name;        //제품 이름

    @Column(name="item_content")
    private String content;     //제품 설명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;      //회원 정보

    @Column(name="item_price")
    private int price;          //제품 가격

    @Column(name="item_count")
    private int count;          //제품 수량

    public Item(String name, String content,Member member, int price, int count) {
        this.name = name;
        this.content = content;
        this.member = member;
        this.price = price;
        this.count = count;
    }

    public void update(String name, String content, Member member, int price, int count){
        this.name = name;
        this.content = content;
        this.member = member;
        this.price = price;
        this.count = count;
    }

    public void removeCount(int count) {
        int restCount = this.count - count;
        if(restCount < 0){
            throw new CustomException(INVALID_ITEM_COUNT);
        }
        this.count = restCount;
    }
}
