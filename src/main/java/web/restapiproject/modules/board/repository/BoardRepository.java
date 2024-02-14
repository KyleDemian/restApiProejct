package web.restapiproject.modules.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import web.restapiproject.modules.board.entity.Board;

@Transactional(readOnly = true)
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
