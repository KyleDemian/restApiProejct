package web.restapiproject.modules.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.restapiproject.modules.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
}
