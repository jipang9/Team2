package Team2.com.orderItem.entity;


import Team2.com.item.entity.Item;
import Team2.com.member.entity.Member;
import Team2.com.order.dto.OrderDto;
import Team2.com.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private int count;

    public void update(Item item, int count){
        this.item = item;
        this.count = count;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    // 생성 메서드
    public static OrderItems createOrderItems(Item item, int count) {
        OrderItems orderItems = new OrderItems();
        orderItems.update(item, count);
        Item getItem = orderItems.getItem();

        getItem.removeCount(count); // Item 수량 감소

        return orderItems;
    }
}
