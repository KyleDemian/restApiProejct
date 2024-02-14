package web.restapiproject.modules.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.restapiproject.modules.board.dto.*;
import web.restapiproject.modules.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<BoardSearchResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable) {
        return boardRepository.getBoardList(boardSearchRequest, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDetailResponse getBoardDetail(Long id) {
        return null;
    }

    @Override
    public Long createBoard(BoardCreateRequest boardCreateRequest) {
        return null;
    }

    @Override
    public void modifyBoard(Long id, BoardModifyRequest boardModifyRequest) {

    }

    @Override
    public void deleteBoard(Long id) {

    }
}
