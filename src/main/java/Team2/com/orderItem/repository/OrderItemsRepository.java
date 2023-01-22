package Team2.com.orderItem.repository;
import Team2.com.order.entity.Order;
import Team2.com.orderItem.entity.OrderItems;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

    Integer countByItem_Id(long itemId);

    @Query("select o from OrderItems o, Item i where o.item.id = i.id and i.member.name = :sellerName")
    Page<OrderItems> checkItemSeller(Pageable pageRequest, @Param("sellerName") String sellerName);


}

