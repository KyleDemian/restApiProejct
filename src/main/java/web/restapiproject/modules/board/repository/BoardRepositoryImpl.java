package web.restapiproject.modules.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import web.restapiproject.modules.board.dto.BoardSearchRequest;
import web.restapiproject.modules.board.dto.BoardSearchResponse;
import web.restapiproject.modules.board.entity.Board;
import web.restapiproject.modules.board.mapper.BoardMapper;

import java.util.List;

import static web.restapiproject.modules.board.entity.QBoard.board;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? board.title.eq(title) : null;
    }

    @Override
    public Page<BoardSearchResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable) {
        List<Board> contents = queryFactory
            .select(Projections.constructor(Board.class,
                    board.id,
                    board.title,
                    board.contents,
                    board.createdAt,
                    board.createdBy
            ))
            .from(board)
            .where(
                titleEq(boardSearchRequest.getSearchKeyword())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Board> boardCount = queryFactory
                .select(board)
                .from(board)
                .where(
                        titleEq(boardSearchRequest.getSearchKeyword())
                );

        List<BoardSearchResponse> response = BoardMapper.INSTANCE.entityToListResponse(contents);

        // 사실 MapStruct를 사용하는 순간부터 1개의 DTO로 작업을 하며,
        // 사용할 파라미터, 무시할 파라미터 등을 설정해서 만들 수 있기때문에 DTO를 여러개를 만들 필요는 없어보임.
        // 반환 타입이 Board -> BoardSearchRequest 즉, MapStruct 를 사용해야함. 여기서!
        return PageableExecutionUtils.getPage(response, pageable, boardCount::fetchCount);
    }
}
