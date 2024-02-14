package web.restapiproject.modules.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.entity.Member;
import web.restapiproject.modules.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberCreateRequest memberCreateRequest) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findOne = memberRepository.findByLoginId(username);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        return User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .roles("ROLE_USER")
                .build()
                ;
    }
}
