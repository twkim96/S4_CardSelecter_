package twk.cardselecter.security.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import twk.cardselecter.member.entity.Member;
import twk.cardselecter.member.repository.MemberRepository;

import java.util.Date;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("UserDetailsService loadUserByUsername(): {}", new Date());
        Member member = memberRepository.findById(username);
        if (member == null){
            throw new UsernameNotFoundException(String.format("'%s'는 존재하지 않는 사용자입니다.", username));
        }
        return new CustomUserDetails(member);
    }
}
