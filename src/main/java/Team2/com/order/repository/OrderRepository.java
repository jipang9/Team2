package Team2.com.order.repository;

import Team2.com.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByMemberId(Pageable pageable, Long memberId);

    Optional<Order> findById(Long orderId);

}

