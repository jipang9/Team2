package Team2.com.item.repository;

import Team2.com.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByOrderByIdDesc(Pageable pageable);

    Optional<Item> findById(Long itemId);
}
