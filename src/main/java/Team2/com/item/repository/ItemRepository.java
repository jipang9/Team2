package Team2.com.item.repository;

import Team2.com.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByOrderByIdDesc(PageRequest pageable);

    Optional<Item> findById(Long itemId);

    Item findByName(String name);

    List<Item> findByContentContaining(String item);

    Page<Item> findByMemberName(Pageable pageable, String sellerName);

    @Query("delete from Item  i where i.member.id=:id")
    void deleteByMember(@Param("id") Long id);
}
