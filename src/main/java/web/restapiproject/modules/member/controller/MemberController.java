package web.restapiproject.modules.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.restapiproject.config.JwtAuthenticationResponse;
import web.restapiproject.config.JwtTokenProvider;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> processLogin(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid data");
        }
        boolean isAuthenticated = memberService.authenticateMember(memberDto);

        if (isAuthenticated) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            memberDto.getLoginId(),
                            memberDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
//            return ResponseEntity.ok("ok");
        } else {
            // @ControllerAdvice를 사용해 글로벌 예외 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
    }

    // 회원 가입
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid MemberCreateRequest memberCreateRequest, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("데이터가 일치하지 않음.");
        }
        Long userId = memberService.signUp(memberCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("사용자 아디 " + userId);
    }

    // 로그 아웃
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
