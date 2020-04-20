package pl.coderslab.drivertips.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TrainingAlreadyExistsException extends RuntimeException {
    public TrainingAlreadyExistsException(String message) {
        super(message);
    }
}
