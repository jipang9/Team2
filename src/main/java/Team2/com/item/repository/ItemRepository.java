package Team2.com.item.repository;

import Team2.com.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByOrderByIdDesc();

    Optional<Item> findById(Long itemId);
}
