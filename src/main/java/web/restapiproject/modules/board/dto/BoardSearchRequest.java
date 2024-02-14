package web.restapiproject.modules.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardSearchRequest {

    private String searchKeyword;

    private String startDt;

    private String endDt;

}
