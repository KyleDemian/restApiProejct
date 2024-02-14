package web.restapiproject.modules.board.dto;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardRequest {

    private String searchKeyword;

    private String startDt;

    private String endDt;
}
