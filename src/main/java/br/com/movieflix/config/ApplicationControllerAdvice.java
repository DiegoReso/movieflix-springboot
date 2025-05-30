package br.com.movieflix.config;

import br.com.movieflix.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUsernameOrPasswordInvalidException(UsernameOrPasswordInvalidException e) {
        return e.getMessage();
    }
}
