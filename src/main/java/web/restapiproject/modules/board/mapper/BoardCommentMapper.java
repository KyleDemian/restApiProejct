package web.restapiproject.modules.board.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import web.restapiproject.modules.board.dto.BoardCommentCreateRequest;
import web.restapiproject.modules.board.entity.BoardComment;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BoardCommentMapper {

    BoardCommentMapper INSTANCE = Mappers.getMapper(BoardCommentMapper.class);

    public BoardComment createRequestToEntity(BoardCommentCreateRequest createRequest);

}
