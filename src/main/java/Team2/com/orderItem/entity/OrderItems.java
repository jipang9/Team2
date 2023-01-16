package Team2.com.orderItem.entity;


import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.entity.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"id", "member", "order", "item"})
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public OrderItems(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public void setOrderAndMember(Order order, Member member){
        this.order = order;
        this.member = member;
    }
}
