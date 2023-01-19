package Team2.com.member.controller;

import Team2.com.member.dto.admin.MembersResponseDto;
import Team2.com.member.dto.admin.SellersResponseDto;
import Team2.com.member.entity.Request;
import Team2.com.member.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Secured({"ROLE_ADMIN"})
@Api(tags = "관리자")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    @ApiOperation(value = "사용자 조회")
    public ResponseEntity<List<MembersResponseDto>> getMemberList
            (@RequestParam(defaultValue = "1") int page,
             @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(200).body(adminService.MemberListPaging(pageable, page - 1));
    }

    @GetMapping("/sellers")
    @ApiOperation(value = "판매자 조회")
    public ResponseEntity<List<SellersResponseDto>> getSellerList
            (@RequestParam(defaultValue = "1") int page,
             @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(200).body(adminService.SellerListPaging(pageable, page-1));
    }

    @GetMapping("/applies")
    @ApiOperation(value = "요청 조회")
    public ResponseEntity<List<Request>> getApplyList() {
        return ResponseEntity.status(200).body(adminService.getAppliesList());
    }

    @PatchMapping("/promoted/{id}")
    @ApiOperation(value = "등업")
    public ResponseEntity<Void> addRoles(@PathVariable Long id) {
        adminService.addRoles(id);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/demotion/{id}")
    @ApiOperation(value = "강등")
    public ResponseEntity<Void> deleteRoles(@PathVariable Long id) {
        adminService.deleteRoles(id);
        return ResponseEntity.status(200).build();
    }


}

