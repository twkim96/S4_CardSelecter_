package twk.cardselecter.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import twk.cardselecter.member.dto.request.JoinRequest;
import twk.cardselecter.member.dto.response.JoinResponse;
import twk.cardselecter.member.entity.Member;

@Mapper
@Repository
public interface MemberRepository {

    Member findById(String id);
    Integer isExistUserId(String id);
    Integer createMember(Member member);

}
