package twk.cardselecter.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import twk.cardselecter.member.dto.request.JoinRequest;
import twk.cardselecter.member.dto.request.LoginRequest;
import twk.cardselecter.member.dto.response.JoinResponse;
import twk.cardselecter.member.dto.response.LoginResponse;
import twk.cardselecter.member.exception.MemberException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService service;
    @GetMapping
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String id){
        HttpStatus status = service.checkIdDuplicate(id);
        log.info("checkIdDuplicate {}", status);
        return new ResponseEntity<>(status);
    }
    @PostMapping("/join")
    public ResponseEntity<JoinResponse> join(@Valid @RequestBody JoinRequest req){
        JoinResponse response = service.join(req);
        log.info("join {}", response);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> join(@Valid @RequestBody LoginRequest req){
        LoginResponse response = service.login(req);
        log.info("login {}", response.getName());
        return ResponseEntity.ok(response);
    }
    /**
     * 요청 DTO 검증 예외처리 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("handleMethodArgumentNotValidException");
        BindingResult bs = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        bs.getFieldErrors().forEach(err -> {
            sb.append(String.format("[%s]: %s.\n입력된 값: %s",
                    err.getField(), err.getDefaultMessage(), err.getRejectedValue()));
        });

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
    /**
     *  사용자 관련 요청 예외처리 핸들러
     */
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleUserException(MemberException e) {
        log.info("handleUserException");
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
