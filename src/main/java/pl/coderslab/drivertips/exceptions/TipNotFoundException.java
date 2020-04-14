package pl.coderslab.drivertips.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipNotFoundException extends RuntimeException {
    public TipNotFoundException(String message) {
        super(message);
    }
}
