package web.restapiproject.modules.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MemberDetail {

    private String id;
    private String loginId;
    private String email;
    private String nickname;
    private String info;
}
