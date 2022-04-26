package uz.iSystem.market.bookmarket.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(ApiRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
