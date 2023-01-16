package Team2.com.member.controller;

import Team2.com.member.service.AdminService;
import Team2.com.member.dto.MembersResponseDto;
import Team2.com.member.dto.SellersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public ResponseEntity<List<MembersResponseDto>> getMemberList(){
        return ResponseEntity.status(200).body(adminService.getMemberList());
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellersResponseDto>> getSellerList(String role){
        return ResponseEntity.status(200).body(adminService.getSellerList(role));
    }

    @PostMapping("/addrole/{id}")
    public ResponseEntity<Void> addRoles(){
        adminService.addRoles();
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deleterole/{id}")
    public ResponseEntity<Void> deleteRoles(){
        adminService.deleteRoles();
        return ResponseEntity.status(200).build();
    }


}
