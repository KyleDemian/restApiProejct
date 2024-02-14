package web.restapiproject.modules.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.security.SecureRandom;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchResponse {

    private Long id;

    private String title;

    private String contents;

    private String searchKeyword;

    private String startDt;

    private String endDt;

//    @QueryProjection
//    public BoardSearchResponse(Long id, String title, String contents, String searchKeyword, String startDt, String endDt) {
//        this.id = id;
//        this.title = title;
//        this.contents = contents;
//        this.searchKeyword = searchKeyword;
//        this.startDt = startDt;
//        this.endDt = endDt;
//    }
}
