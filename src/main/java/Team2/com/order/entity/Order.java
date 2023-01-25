package Team2.com.order.entity;


import Team2.com.member.entity.Member;
import Team2.com.orderItem.entity.OrderItems;
import Team2.com.security.exception.CustomException;
import Team2.com.security.exception.ErrorCode;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderItems> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private String orderStatus = "N";



    public void setMember(Member member){
        this.member=member;
    }

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
        this.orderStatus = "Y";
    }

    public void checkByOrderStatus(){ // 주문 상태 확인
        if(this.getOrderStatus().equals("Y"))
            throw new CustomException(ErrorCode.ALREADY_ORDER);
    }

    public void checkByCustomer(Member member){ // 해당 주문이 해당 유저의 주문인지 아닌지 확인
        if(this.getMember().getName().equals(member.getName()))
            return ;
        else
            throw new CustomException(ErrorCode.NOT_MATCH_INFORMATION);

    }
}
