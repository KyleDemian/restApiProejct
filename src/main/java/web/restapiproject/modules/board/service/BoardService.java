package web.restapiproject.modules.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.restapiproject.modules.board.dto.*;

public interface BoardService {
    Page<BoardSearchResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable);

    BoardDetailResponse getBoardDetail(Long id);

    Long createBoard(BoardCreateRequest boardCreateRequest);

    void modifyBoard(Long id, BoardModifyRequest boardModifyRequest);

    void deleteBoard(Long id);

    Long createBoardComments(Long id, BoardCommentRequest boardCommentRequest);
}
