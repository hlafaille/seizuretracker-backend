package xyz.hlafaille.seizuretracker.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ SessionEntityMissingException.class })
    public String handleSessionEntityMissingException() {
        return "redirect:/login";
    }
}
