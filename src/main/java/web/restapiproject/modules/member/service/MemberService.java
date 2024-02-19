package web.restapiproject.modules.member.service;
import org.springframework.stereotype.Service;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.dto.MemberDto;

@Service
public interface MemberService {
    Long signUp(MemberCreateRequest memberCreateRequest);

    boolean authenticateMember(MemberDto memberDto);
}
