package web.restapiproject.modules.board.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import web.restapiproject.modules.board.dto.BoardSearchRequest;
import web.restapiproject.modules.board.dto.BoardSearchResponse;
import web.restapiproject.modules.board.entity.Board;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
//        , uses ={ BoardMapper.class}
)
// https://jiwondev.tistory.com/250
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    public Board requestToEntity(BoardSearchRequest boardSearchRequest);

    // 객체를 리스트 형태로 담기 위해서는 동일한게 1개 더 있어야 함.
    public BoardSearchResponse entityToListResponse(Board board);

    public List<BoardSearchResponse> entityToListResponse(List<Board> board);

}
