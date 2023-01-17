package Team2.com.member.repository;

import Team2.com.member.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("delete from Request r where r.user = :member_id")
    void deleteById(@Param("member_id")Long member_id);

}
