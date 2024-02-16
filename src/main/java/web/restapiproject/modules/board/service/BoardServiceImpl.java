package web.restapiproject.modules.board.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.restapiproject.modules.board.dto.*;
import web.restapiproject.modules.board.entity.Board;
import web.restapiproject.modules.board.mapper.BoardMapper;
import web.restapiproject.modules.board.repository.BoardRepository;

import java.util.List;

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
        Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        BoardDetailResponse boardDetailResponse = BoardMapper.INSTANCE.entityToDetailResponse(board);
        return boardDetailResponse;
    }

    @Override
    public Long createBoard(BoardCreateRequest boardCreateRequest) {
        Long boardId = boardRepository.save(BoardMapper.INSTANCE.createRequestToEntity(boardCreateRequest)).getId();
        return boardId;
    }

    @Override
    public void modifyBoard(Long id, BoardModifyRequest boardModifyRequest) {
        Board board = BoardMapper.INSTANCE.modifyRequestToEntity(boardModifyRequest);
        board.setId(id);

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
