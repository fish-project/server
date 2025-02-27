package src.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import src.view.forbiden;

@RestControllerAdvice
public class ForbidenAdvice {

    // Bắt lỗi MissingServletRequestParameterException
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new forbiden(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    // Bắt lỗi HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseError(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new forbiden(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
