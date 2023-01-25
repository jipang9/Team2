package Team2.com.order.repository;

import Team2.com.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByMemberId(Pageable pageable, Long memberId);

    Optional<Order> findById(Long orderId);

    boolean existsByMemberId(Long id);

    /** 필요한가? *
     */
    //     이 query는 모든 주문을 다 취소시켜버림
    @Transactional
    @Modifying
    void deleteAllByMemberId(Long id);

}

