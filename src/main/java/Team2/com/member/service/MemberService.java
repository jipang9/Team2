package Team2.com.member.service;

import Team2.com.member.dto.MembersResponseDto;
import Team2.com.member.dto.SellersResponseDto;

import java.util.List;

public interface MemberService{

    List<MembersResponseDto> getMemberList();

    List<SellersResponseDto> getSellersList(String role);

}
