package Team2.com.member.controller;

import Team2.com.member.service.AdminServiceImpl;
import Team2.com.member.service.MembersResponseDto;
import Team2.com.member.service.SellersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServiceImpl adminServiceImpl;

    @GetMapping("/members")
    public ResponseEntity<List<MembersResponseDto>> getMemberList(){

        // 사용자(일반유저) 정보들을 List 형식으로 넘겨준다  -> 어떤 정보를 가지고 올래?

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellersResponseDto>> getSellerList(){

        // 사용자(판매자) 정보들을 가지고 올 것(List) -> 어떤 정보를 가지고 올래?

        return ResponseEntity.status(200).build(); // 이 부분 body로 바꿀예정
    }

    @PostMapping("/addrole/{id}")
    public ResponseEntity<Void> addRoles(){
        // 사용자가 권한 Up을 요청하는 table을 하나 만들어야겠네 어떤 권한을 원하는지 정보를 담은
        // 그래서 관리자는 그 table을 확인하고 해당 메서드를 통해 해당 유저의 권한을 부여한다 (한명당 권한은 많이 가질 수 있기에, update가 아닌 post)
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deleterole/{id}")
    public ResponseEntity<Void> deleteRoles(){
        // addRoles와 마찬가지로 똑같이 진행 -> 그래서 해당 (status enum을 만들어야 하나?)
        return ResponseEntity.status(200).build();
    }


}
