package pl.coderslab.drivertips.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(String message) {
        super(message);
    }
}
