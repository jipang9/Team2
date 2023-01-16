package Team2.com.order.entity;


import Team2.com.member.entity.Member;
import Team2.com.orderItem.entity.OrderItems;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();

    public Order(Member member, OrderItems... orderItemList) {
        this.member = member;
        for (OrderItems orderItem : orderItemList) {
            addOrderItems(orderItem);

        }
    }

    public void addOrderItems(OrderItems orderItems){
        this.orderItems.add(orderItems);
        orderItems.setOrderAndMember(this, this.member);
    }
}
