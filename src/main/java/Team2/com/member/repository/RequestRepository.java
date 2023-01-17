package Team2.com.member.repository;

import Team2.com.member.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("delete from Request r where r.user = :member_id")
    void deleteById(@Param("member_id")Long member_id);

    @Query("select r from Request r where r.user=:id")
    Optional<Request> findByMember(@Param("id") Long id);

}
