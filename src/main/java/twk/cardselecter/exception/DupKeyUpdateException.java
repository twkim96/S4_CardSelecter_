package twk.cardselecter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@Getter
public class DupKeyUpdateException extends SQLIntegrityConstraintViolationException {
    private final HttpStatus status;
    public DupKeyUpdateException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
