package web.restapiproject.modules.member.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import web.restapiproject.modules.member.entity.Member;
import web.restapiproject.modules.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class MemberServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    @Test
    public void 회원가입테스트(){
        // given
        Member member = Member.builder()
                .loginId("testUser")
                .password("password")
                .build();

        //when
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        //then
        Member savedMember = memberRepository.save(member);

        verify(memberRepository, times(1)).save(member);

        assertEquals("testUser", savedMember.getUsername());
        assertEquals("password", savedMember.getPassword());
    }

}