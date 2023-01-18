//package Team2.com.member.controller;
//
//import Team2.com.member.dto.admin.MembersResponseDto;
//import Team2.com.member.dto.admin.SellersResponseDto;
//import Team2.com.member.entity.Request;
//import Team2.com.member.service.admin.AdminService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/admin")
//@Secured({"ROLE_ADMIN"})
//public class AdminController {
//
//    private final AdminService adminService;
//
//
//    @GetMapping("/members")
//    public ResponseEntity<List<MembersResponseDto>> getMemberList
//            (@RequestParam(defaultValue = "1") int page,
//             @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        return ResponseEntity.status(200).body(adminService.MemberListPaging(pageable, page - 1));
//
//    }
//
//    @GetMapping("/sellers")
//    public ResponseEntity<List<SellersResponseDto>> getSellerList
//            (@RequestParam(defaultValue = "1") int page,
//             @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        return ResponseEntity.status(200).body(adminService.SellerListPaging(pageable, page-1));
//    }
//
//
//    @GetMapping("/applys")
//    public ResponseEntity<List<Request>> getApplyList() {
//        return ResponseEntity.status(200).body(adminService.getAppliesList());
//    }
//
//    // 여기서 들어온 id 값은 사용자가 권한을 요청한 테이블의 id인데... 해당 요청에 의존적이다는 느낌이 있다..
//    // 과연 요청에 의존적인 컨트롤러를 구상하는 것이 맞는건지...
//    @PatchMapping("/promoted/{id}")
//    public ResponseEntity<Void> addRoles(@PathVariable Long id) {
//        adminService.addRoles(id);
//        return ResponseEntity.status(201).build();
//    }
//
//    @PatchMapping("/demotion/{id}")
//    public ResponseEntity<Void> deleteRoles(@PathVariable Long id) {
//        adminService.deleteRoles(id);
//        return ResponseEntity.status(200).build();
//    }
//
//
//}
