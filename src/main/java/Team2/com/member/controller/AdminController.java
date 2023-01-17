package Team2.com.member.controller;

import Team2.com.member.service.admin.AdminService;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public ResponseEntity<List<MembersResponseDto>> getMemberList(){
        return ResponseEntity.status(200).body(adminService.getMemberList());
    }

    @GetMapping("/sellers")
        public ResponseEntity<List<SellersResponseDto>> getSellerList(){
            return ResponseEntity.status(200).body(adminService.getSellerList());
        }

        @PostMapping("/addrole/{id}")
        public ResponseEntity<Void> addRoles(@PathVariable Long id){
            adminService.addRoles(id);
            return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deleterole/{id}")
    public ResponseEntity<Void> deleteRoles(){
        adminService.deleteRoles();
        return ResponseEntity.status(200).build();
    }


}
