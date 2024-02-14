package web.restapiproject.modules.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    public MemberDto modelToDto(Member member);
    public Member dtoToModel(MemberDto memberDto);
}
