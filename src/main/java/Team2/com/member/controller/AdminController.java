package Team2.com.member.controller;

import Team2.com.member.dto.AppliesResponseDto;
import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Request;
import Team2.com.member.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public ResponseEntity<List<MembersResponseDto>> getMemberList() {
        return ResponseEntity.status(200).body(adminService.getMemberList());
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellersResponseDto>> getSellerList() {
        return ResponseEntity.status(200).body(adminService.getSellerList());
    }


    @GetMapping("/applys")
    public ResponseEntity<List<Request>> getApplyList(){
        return org.springframework.http.ResponseEntity.status(200).body(adminService.getAppliesList());
    }

    @PatchMapping("/promoted/{id}")
    public ResponseEntity<Void> addRoles(@PathVariable Long id) {
        adminService.addRoles(id);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/demotion/{id}")
    public ResponseEntity<Void> deleteRoles(@PathVariable Long id) {
        adminService.deleteRoles(id);
        return ResponseEntity.status(200).build();
    }


}
