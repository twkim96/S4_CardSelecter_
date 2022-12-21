package twk.cardselecter.member;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.member.dto.request.JoinRequest;
import twk.cardselecter.member.dto.response.JoinResponse;
import twk.cardselecter.member.exception.MemberException;
import twk.cardselecter.member.repository.MemberRepository;
import twk.cardselecter.security.jwt.JwtTokenUtil;

@Service
@Transactional
@AllArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * @param id 조회하고자 하는 id
     * @return id가 이미 존재하면, 사용중인 아이디 메시지 출력
     */
    public HttpStatus checkIdDuplicate(String id) {
        isExistUserId(id); //가독성을 위해 함수를 따로 빼둠
        return HttpStatus.OK;
    }
    @Transactional
    public JoinResponse join(JoinRequest req){
        isExistUserId(req.getId());
        saveMember(req);
        authenticate(req);
        return new JoinResponse(req.getId());
    }

    private void authenticate(JoinRequest req) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getId(), req.getPwd()))
        } catch (DisabledException e){
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            System.out.println(req.getId() + req.getPwd() + "koko");
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private void saveMember(JoinRequest req) {
        String encodePwd = encoder.encode(req.getPwd());
        int result = repository.createMember(
                new JoinRequest(req.getId(), encodePwd, req.getName(), req.getEmail())
        );
        if(result == 0)
            throw new MemberException("회원 등록을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void isExistUserId(String id) {
        Integer result = repository.isExistUserId(id);
        if (result == 1)
            throw new MemberException("이미 사용중인 아이디입니다.", HttpStatus.BAD_REQUEST);
    }
}
