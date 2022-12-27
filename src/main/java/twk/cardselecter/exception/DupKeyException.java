package twk.cardselecter.exception;

import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

@Getter
public class DupKeyException extends RuntimeException {
    private final HttpStatus status;
    public DupKeyException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
