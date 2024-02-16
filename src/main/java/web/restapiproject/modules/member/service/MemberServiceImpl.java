package web.restapiproject.modules.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.entity.Member;
import web.restapiproject.modules.member.mapper.MemberMapper;
import web.restapiproject.modules.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signUp(MemberCreateRequest memberCreateRequest) {
        Member member = MemberMapper.INSTANCE.createRequestToEntity(memberCreateRequest);
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberRepository.findByLoginId(loginId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        return member;
//        return User.builder()
//                .username(member.getLoginId())
//                .password(member.getPassword())
//                .roles("ROLE_USER")
//                .build()
//                ;
    }
}
