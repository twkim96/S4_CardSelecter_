package twk.cardselecter.member;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

}
