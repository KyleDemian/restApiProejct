package web.restapiproject.modules.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import web.restapiproject.modules.board.dto.*;
import web.restapiproject.modules.board.service.BoardService;
import web.restapiproject.modules.member.entity.Member;

import java.net.URI;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    // https://adjh54.tistory.com/151 스웨거를 통한 문서 정리

    private final BoardService boardService;

    // 목록 조회
    @GetMapping("/boards")
    public ResponseEntity<Page<BoardSearchResponse>> getBoardList(
            @ModelAttribute BoardSearchRequest boardSearchRequest, Pageable pageable) {
        return ResponseEntity.ok(boardService.getBoardList(boardSearchRequest, pageable));
    }

    // 상세 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardDetailResponse> getBoardDetail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(boardService.getBoardDetail(id));
    }

    // 글 등록
    // Errors: 객체의 상태에 대한 정보를 확인하고, 에러 메시지를 관리하는 방법을 제공
    // BindingResult: Errors를 확장하며, DataBinder가 고려해야 할 특정 컨텍스트 정보(예: 요구되는 필드, 특정 필드에 대한 PropertyEditor 등)를 추가로 제공합니다.
    // 또한, 다른 Spring 모듈과 통합하는데 필요한 메소드(예: ModelAndView를 위한 메소드 등)도 추가로 제공
    @PostMapping("/boards")
    public ResponseEntity<Void> createBoard(
            // 로그인 했을경우만 글쓸 수 있도록 변경 해야함.
            @AuthenticationPrincipal Member member
            , @RequestBody @Valid BoardCreateRequest boardCreateRequest
            , BindingResult bindingResult) {

        if (member == null) {
            throw new AccessDeniedException("사용자 로그인 이후 글 작성 가능");
        }

        if (bindingResult.hasErrors()) {
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalArgumentException("유효성 검사 오류: " + errMsg);
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
                                            @RequestBody @Valid BoardModifyRequest boardModifyRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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

    /**
     * 댓글 작성 영역 추가
     */
    @PostMapping("/boards/{id}/comments")
    public ResponseEntity<Void> createBoardComment(@PathVariable Long id,
                                                   @RequestBody @Valid BoardCommentCreateRequest boardCommentCreateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("데이터 확인 필요.");
        }

//        Long createBoardId = boardService.createBoardComments(member, id ,boardCommentRequest);
        Long createBoardId = boardService.createBoardComments(id, boardCommentCreateRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createBoardId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<Long> modifyBoardComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                                   @RequestBody @Valid BoardCommentCreateRequest boardCommentCreateRequest,
                                                   BindingResult bindingResult) {
        boardService.modifyBoardComment(boardId, commentId, boardCommentCreateRequest);

        return ResponseEntity.ok(commentId);
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<Long> deleteBoardComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        boardService.deleteBoardComment(boardId, commentId);

        return ResponseEntity.ok(commentId);
    }
}
