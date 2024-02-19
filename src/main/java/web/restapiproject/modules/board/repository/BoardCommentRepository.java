package web.restapiproject.modules.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.restapiproject.modules.board.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
