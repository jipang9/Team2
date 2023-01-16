package Team2.com.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    @Override
    public List<MembersResponseDto> getMemberList() {
        return null;
    }

    @Override
    public List<SellersResponseDto> getSellerList() {
        return null;
    }

    @Override
    public void addRoles() {

    }

    @Override
    public void deleteRoles() {

    }
}
