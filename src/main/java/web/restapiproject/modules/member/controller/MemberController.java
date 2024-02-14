package web.restapiproject.modules.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "";
    }

    @PostMapping("/login-process")
    public ResponseEntity login(@ModelAttribute MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@ModelAttribute MemberCreateRequest memberCreateRequest, Errors errors) throws Exception {
        if (errors.hasErrors()) {

        }
        memberService.signUp(memberCreateRequest);

        return ResponseEntity.ok("");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("Logout 완료");
    }
}
