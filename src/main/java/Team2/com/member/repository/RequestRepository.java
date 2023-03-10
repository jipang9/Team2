package Team2.com.member.repository;

import Team2.com.member.dto.member.ApplyRequestDto;
import Team2.com.member.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {


    @Query("select r from Request r where r.user=:id")
    Optional<Request> findByMember(@Param("id") Long id);

    List<Request> findAll();

    boolean existsByUser(Long id);


    @Modifying
    @Transactional
    @Query("delete from Request  r where r.user = :id")
    void deleteByUserId(@Param("id") Long id);
}
