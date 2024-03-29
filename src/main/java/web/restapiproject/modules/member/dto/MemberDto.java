package web.restapiproject.modules.member.dto;

import jakarta.persistence.Column;
import lombok.*;
import web.restapiproject.enums.Gender;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    private String loginId;
    private String password;
}
