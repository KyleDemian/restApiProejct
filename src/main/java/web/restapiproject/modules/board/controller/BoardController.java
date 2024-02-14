package web.restapiproject.modules.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import web.restapiproject.modules.board.dto.*;
import web.restapiproject.modules.board.service.BoardService;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 목록 조회
    @GetMapping("/boards")
    public ResponseEntity<Page<BoardSearchResponse>> getBoardList(
            @ModelAttribute BoardSearchRequest boardSearchRequest, Pageable pageable) {
        return ResponseEntity.ok(boardService.getBoardList(boardSearchRequest, pageable));
    }

    // 상세 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardDetailResponse> getBoardDetail(@PathVariable("id") Long id){
        return ResponseEntity.ok(boardService.getBoardDetail(id));
    }

    // 글 등록
    @PostMapping("/boards")
    public ResponseEntity<Void> createBoard(@ModelAttribute @Valid BoardCreateRequest boardCreateRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("데이터가 잘못 들어왔음");
        }
        Long createBoardId = boardService.createBoard(boardCreateRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createBoardId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // 글 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<Void> modifyBoard(@PathVariable("id") Long id,
                                            @ModelAttribute @Valid BoardModifyRequest boardModifyRequest,
                                            Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("데이터가 잘못 들어왔음");
        }
        boardService.modifyBoard(id, boardModifyRequest);

        return ResponseEntity.noContent().build();
    }

    // 글 삭제
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
