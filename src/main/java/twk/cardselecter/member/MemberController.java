package twk.cardselecter.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twk.cardselecter.member.exception.MemberException;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    /* 요청 DTO 검증 예외처리 핸들러 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("UserController handleMethodArgumentNotValidException " + new Date());

        BindingResult bs = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        bs.getFieldErrors().forEach(err -> {
            sb.append(String.format("[%s]: %s.\n입력된 값: %s",
                    err.getField(), err.getDefaultMessage(), err.getRejectedValue()));
        });

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    /* 사용자 관련 요청 예외처리 핸들러 */
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleUserException(MemberException e) {
        System.out.println("UserController handlerUserException " + new Date());

        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
