//package Team2.com.order.entity;
//
//
//import Team2.com.member.entity.BaseEntity;
//import Team2.com.orderItem.entity.OrderItems;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//public class OrderHistory extends BaseEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long orderNum; // 주문번호
//
//    private String sellerName; // 판매자 이름
//
//    private String buyerName; // 구매자 이름
//
//    private Long turnover; // 거래액
//
//
//    private Long itemCount; // 물건 수량
//
//}
