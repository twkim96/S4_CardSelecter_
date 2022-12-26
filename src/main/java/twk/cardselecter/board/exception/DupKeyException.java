package twk.cardselecter.board.exception;

import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

@Getter
public class DupKeyException extends DuplicateKeyException {
    private final HttpStatus status;
    public DupKeyException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
