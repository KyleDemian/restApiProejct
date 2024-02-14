package web.restapiproject.modules.board.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;

}
