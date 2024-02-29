package web.restapiproject.modules.board.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import web.restapiproject.modules.board.dto.BoardCreateRequest;
import web.restapiproject.modules.board.entity.Board;
import web.restapiproject.modules.board.repository.BoardRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class BoardServiceImplTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    public void testGetBoardList() {
        // given

        //when

        //then

    }

    @Test
    public void testCreateBoard() {
        // Given
        BoardCreateRequest boardCreateRequest = new BoardCreateRequest();
        boardCreateRequest.setTitle("Title");
        boardCreateRequest.setContents("Contents");

        Board expectedBoard = Board.builder()
                .title(boardCreateRequest.getTitle())
                .contents(boardCreateRequest.getContents())
                .build();

        Mockito.when(boardRepository.save(any(Board.class))).thenReturn(expectedBoard);

        // When
        Long actualBoardId = boardService.createBoard(boardCreateRequest);

        // Then (Verifying the results)
        assertEquals(expectedBoard.getId(), actualBoardId);

        // Verify
        verify(boardRepository, times(1)).save(any(Board.class));
    }

    @Test
    public void testModifyBoard() {
        // given

        // when

        // then

    }

    @Test
    public void testDeleteBoard() {
        // given

        // when

        // then

    }

}