package twk.cardselecter.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.member.dto.request.JoinRequest;
import twk.cardselecter.member.dto.request.LoginRequest;
import twk.cardselecter.member.dto.response.JoinResponse;
import twk.cardselecter.member.entity.Member;
import twk.cardselecter.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void checkIdDuplicate() {
        Member member = Member.builder().id("id").build();
        HttpStatus status = memberService.checkIdDuplicate(member.getId());
        System.out.println(status);
    }

    @Test
    void join() {
        JoinResponse join = memberService.join(new JoinRequest("kkjjkkjj",
                "$2a$10$SQDC6eHKq.kf/YbVgDTDxeOt4TmPxcKdCfQh0A2dvj0/nxfkgnMGK", "안녕", "mll523"));
        System.out.println(join);
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("kkjjkkjj", "test1234");
        memberService.login(loginRequest);
    }
}