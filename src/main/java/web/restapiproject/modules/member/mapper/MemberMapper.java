package web.restapiproject.modules.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import web.restapiproject.modules.board.mapper.BoardMapper;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.dto.MemberModifyRequest;
import web.restapiproject.modules.member.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

        MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

        public Member createRequestToEntity(MemberCreateRequest createRequest);

        public Member modifyRequestToEntity(MemberModifyRequest modifyRequest);


}
