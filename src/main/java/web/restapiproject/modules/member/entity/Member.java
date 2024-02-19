package web.restapiproject.modules.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.restapiproject.common.entity.BaseEntity;
import web.restapiproject.enums.Gender;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private String info;

    @Builder
    public Member(String loginId, String password, String auth){
        this.loginId = loginId;
        this.password = password;
    }

    @Builder
    public Member(String loginId, String password, String email, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    // 사용자 고유 ID 반환
    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
