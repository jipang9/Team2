package Team2.com.member.repository;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Member;

import Team2.com.member.entity.MemberRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select m from Member m order by m.id desc")
    List<MembersResponseDto> findAllByMembers();

    Optional<Member> findByEmail(String email);

    @Query("select m from Member m where m.role='SELLER' order by m.id desc")
    List<SellersResponseDto> findAllBySellers();

//    @Query("select m from Member m where m.role='SELLER' order by m.id desc")
//    List<Member> findAllBySellersPaging(Pageable pageable);

    Optional<Member> findByNameAndAndRole(String name, MemberRoleEnum roleEnum);


    @Query("select m from Member m where m.id=:id and m.role='SELLER'")
    List<SellersResponseDto> findBySellerId(@Param("id") Long sellerId);

}
