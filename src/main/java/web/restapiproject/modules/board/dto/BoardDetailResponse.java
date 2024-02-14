package web.restapiproject.modules.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDetailResponse {

    private String id;
    private String title;
    private String contents;
    private String createdBy;
}
