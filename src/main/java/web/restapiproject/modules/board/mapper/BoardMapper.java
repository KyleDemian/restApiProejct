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

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    public Board requestToEntity(BoardSearchRequest boardSearchRequest);

    public BoardSearchResponse entityToResponse(Board board);
    public List<BoardSearchResponse> entityToListResponse(Board board);

}
