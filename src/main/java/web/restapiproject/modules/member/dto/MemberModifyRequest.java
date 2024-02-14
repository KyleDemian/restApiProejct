package web.restapiproject.modules.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberModifyRequest {

    @NotBlank(message = "비밀번호에 공백은 불가능합니다.")
    @Length(min = 8, max = 50)
    private String password;

    @NotBlank(message = "닉네임을 입력 해야 합니다.")
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;
    private String info;
}
