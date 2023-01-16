package Team2.com.security.security;

import Team2.com.member.entity.Member;
import Team2.com.security.MemberRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    //인증이 완료된 사용자 추가
    private final Member member;
    private final String username;

    public UserDetailsImpl(Member member, String username) {
        this.member = member;
        this.username = username;
    }

    //인증완료된 User 를 가져오는 Getter
    public Member getMember() {
        return member;
    }

    //사용자의 권한 GrantedAuthority 로 추상화 및 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        MemberRoleEnum role = member.getRole();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    //사용자의 ID, PWD Getter
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}