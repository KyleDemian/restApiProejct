package web.restapiproject.modules.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import web.restapiproject.modules.board.mapper.BoardMapper;
import web.restapiproject.modules.member.dto.MemberCreateRequest;
import web.restapiproject.modules.member.dto.MemberDto;
import web.restapiproject.modules.member.dto.MemberModifyRequest;
import web.restapiproject.modules.member.entity.Member;

@Mapper(componentModel = "spring",
//        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

        MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

        //source : 소스 필드
        //target : 타켓 필드
        //ignore : 매핑시키지 않기
        //expression : 직접 구현, 간단한 java 코드 사용 가능
        //constant : 상수 값
        //defaultValue : 디폴트값
        //qualifiedByName : 별도 메소드 분리하여 복잡하게 매핑
        //beforeMapping / afterMapping : 매핑 전후로 수행할 로직
        //condition : 맵핑 시 모든 필드들에 대해 공통 함수를 적용할 때 사용 (null 이나 빈 값 체크 시 )
        // → 전체 필드에 적용되므로, 공통일 경우에만 쓸 것.

//        @Mapping(target="email", source="email")
//        @Mapping(target = "nickname", source = "nickname")
//        @Mapping(target = "info", ignore = true)
        public Member createRequestToEntity(MemberCreateRequest createRequest);

        public Member modifyRequestToEntity(MemberModifyRequest modifyRequest);


}
