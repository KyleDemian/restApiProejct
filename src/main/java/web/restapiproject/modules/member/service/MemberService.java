package web.restapiproject.modules.member.service;
import org.springframework.stereotype.Service;
import web.restapiproject.modules.member.dto.MemberCreateRequest;

@Service
public interface MemberService {
    void signUp(MemberCreateRequest memberCreateRequest);
}
