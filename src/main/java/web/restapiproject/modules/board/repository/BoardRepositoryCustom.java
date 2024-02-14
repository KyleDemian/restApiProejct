package web.restapiproject.modules.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.restapiproject.modules.board.dto.BoardSearchRequest;
import web.restapiproject.modules.board.dto.BoardSearchResponse;
import web.restapiproject.modules.board.entity.Board;

public interface BoardRepositoryCustom {

    Page<BoardSearchResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable);
}
