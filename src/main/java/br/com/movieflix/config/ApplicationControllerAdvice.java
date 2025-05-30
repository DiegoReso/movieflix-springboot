package br.com.movieflix.config;

import br.com.movieflix.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUsernameOrPasswordInvalidException(UsernameOrPasswordInvalidException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
      Map<String, String> errors = new LinkedHashMap<>();
      e.getBindingResult().getAllErrors().forEach((error) ->
              errors.put(((FieldError) error).getField(), error.getDefaultMessage())
      );

      return errors;
    }
}
