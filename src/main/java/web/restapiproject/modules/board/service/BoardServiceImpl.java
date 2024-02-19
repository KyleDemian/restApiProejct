package web.restapiproject.modules.board.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.restapiproject.modules.board.dto.*;
import web.restapiproject.modules.board.entity.Board;
import web.restapiproject.modules.board.entity.BoardComment;
import web.restapiproject.modules.board.mapper.BoardCommentMapper;
import web.restapiproject.modules.board.mapper.BoardMapper;
import web.restapiproject.modules.board.repository.BoardCommentRepository;
import web.restapiproject.modules.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

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
//        Long boardId = boardRepository.save(BoardMapper.INSTANCE.createRequestToEntity(boardCreateRequest)).getId();
        Board board = BoardMapper.INSTANCE.createRequestToEntity(boardCreateRequest);
//        board.setCreatedBy("USER");
        Long boardId = boardRepository.save(board).getId();
        return boardId;
    }

    @Override
    public void modifyBoard(Long id, BoardModifyRequest boardModifyRequest) {
        Board board = BoardMapper.INSTANCE.modifyRequestToEntity(boardModifyRequest);
        board = board.builder()
                .id(id)
                .build();
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Long createBoardComments(Long id, BoardCommentCreateRequest boardCommentCreateRequest) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재 하지 않음"));

        BoardComment boardComment = BoardCommentMapper.INSTANCE.createRequestToEntity(boardCommentCreateRequest);
        boardComment.setBoard(board);

        return boardCommentRepository.save(boardComment).getId();
    }

    @Override
    public void modifyBoardComment(Long boardId, Long commentId, BoardCommentCreateRequest boardCommentCreateRequest) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재 하지 않음"));
        BoardComment boardComment = boardCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 게시판 글 존재 하지 않음"));
        boardComment.update(boardCommentCreateRequest.getComment());
        boardCommentRepository.save(boardComment);
    }

    @Override
    public void deleteBoardComment(Long boardId, Long commentId) {
        if (!boardRepository.existsById(boardId)) {
            throw new IllegalArgumentException("게시글이 존재하지 않음");
        }
//        boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재 하지 않음"));

        boardCommentRepository.deleteById(commentId);
    }

}
