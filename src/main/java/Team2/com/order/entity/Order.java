package Team2.com.order.entity;


import Team2.com.member.entity.Member;
import Team2.com.orderItem.entity.OrderItems;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
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

    public void setMember(Member member){
    @Column(nullable = false)
    private boolean orderStatus = false;

    public Order(Member member, OrderItems... orderItemList) {
        this.member = member;
        for (OrderItems orderItem : orderItemList) {
            addOrderItems(orderItem);

        }
    }

    public void addOrderItems(OrderItems orderItems){
        this.orderItems.add(orderItems);
        orderItems.setOrder(this);
    }


    // 생성 메서드
    public static Order createOrder(Member member, List<OrderItems> orderItems){
        Order order = new Order();
        order.setMember(member);
        for (OrderItems orderItem : orderItems) {
            order.addOrderItems(orderItem);
        }
        return order;
    }

    public void updateOrderStatus(){
        this.orderStatus = true;
    }
}
