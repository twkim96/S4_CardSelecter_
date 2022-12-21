package twk.cardselecter.member.exception;

import org.springframework.http.HttpStatus;

public class MemberException extends RuntimeException {

    private final HttpStatus status;

    public MemberException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
