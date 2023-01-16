package Team2.com.member.repository;

import Team2.com.member.dto.SellersResponseDto;
import Team2.com.member.entity.Member;
import Team2.com.member.dto.MembersResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    @Query("select m from Member m order by m.id desc")
    List<MembersResponseDto> findAllByMembers(); // 사용자 전체가지고 와야함 + responseDto에 데이터 추가할 것.


    @Query("select m from Member m where m.role=:SELLER")
    List<SellersResponseDto> findAllBySellers(@Param("SELLER")String SELLER);

}
