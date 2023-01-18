//package Team2.com.order.repository;
//
//import Team2.com.order.entity.Order;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Override
//    Page<Order> findAll(Pageable pageable);
//
//    Optional<Order> findById(Long orderId);
//
//}
