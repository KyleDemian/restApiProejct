package web.restapiproject.modules.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberCreateRequest {

    @NotBlank(message = "아이디는 필수 값입니다.")
    @Length(min = 2, max = 20)
    private String loginId;

    @Email(message = "이메일 형식에 맞게 작성 해야합니다.")
    @NotBlank(message = "이메일은 필수 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호에 공백은 불가능합니다.")
    @Length(min = 8, max = 50)
    private String password;

    @NotBlank(message = "닉네임을 입력 해야 합니다.")
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;
    private String info;
}
