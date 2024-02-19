package web.restapiproject.modules.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardCommentRequest {

    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    private String comment;
}
