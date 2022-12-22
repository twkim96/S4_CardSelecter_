package twk.cardselecter.member;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.member.dto.request.JoinRequest;
import twk.cardselecter.member.dto.request.LoginRequest;
import twk.cardselecter.member.dto.response.JoinResponse;
import twk.cardselecter.member.dto.response.LoginResponse;
import twk.cardselecter.member.entity.Member;
import twk.cardselecter.member.exception.MemberException;
import twk.cardselecter.member.repository.MemberRepository;
import twk.cardselecter.security.jwt.JwtTokenUtil;

/**
 *
 */
@Service
@Transactional
@AllArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public HttpStatus checkIdDuplicate(String id) {
        isExistUserId(id);
        return HttpStatus.OK;
    }

    @Transactional
    public JoinResponse join(JoinRequest req){
        isExistUserId(req.getId());
        saveMember(req);
        authenticate(req.getId(), req.getPwd());
        return new JoinResponse(req.getId());
    }

    public LoginResponse login(LoginRequest req){
        authenticate(req.getId(), req.getPwd());
        Member m = repository.findById(req.getId());
        return new LoginResponse(m.getId(), m.getName() ,getJwtToken(req));
    }

    /**
     * jwtToken 을 생성하는 메소드, loadUserByUsername 은 사용자 이름을 기준으로 DB의 사용자 정보를 찾는다.
     */
    private String getJwtToken(LoginRequest req) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getId());
        return jwtTokenUtil.generateToken(userDetails);
    }

    /**
     * 저장한 멤버를 인증(UsernamePasswordAuthenticationToken)
     */
    private void authenticate(String id, String pwd) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, pwd));
        } catch (DisabledException e){
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * repository 를 통해 멤버 저장
     */
    private void saveMember(JoinRequest req) {
        String encodePwd = encoder.encode(req.getPwd());
        int result = repository.createMember(
                new JoinRequest(req.getId(), encodePwd, req.getName(), req.getEmail())
        );
        if(result == 0)
            throw new MemberException("회원 등록을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 아이디 중복 확인
     */
    private void isExistUserId(String id) {
        Integer result = repository.isExistUserId(id);
        if (result == 1)
            throw new MemberException("이미 사용중인 아이디입니다.", HttpStatus.BAD_REQUEST);
    }
}
