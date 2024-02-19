package web.restapiproject.modules.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> processLogin(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid data");
        }
        boolean isAuthenticated = memberService.authenticateMember(memberDto);

        if (isAuthenticated) {
            return ResponseEntity.ok(HttpStatus.OK.toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
    }

    // 회원 가입
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid MemberCreateRequest memberCreateRequest, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("데이터가 일치하지 않음.");
        }
        Long userId = memberService.signUp(memberCreateRequest);

        return ResponseEntity.ok("회원 가입 완료 ::: " + userId);
    }

    // 로그 아웃
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("Logout 완료");
    }
}
