package com.library.library_web_api.excpetion_handlers;

import com.library.library_web_api.webapi.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleNotFoundEx(NotFoundException ex, RedirectAttributes redirectAttributes){
        String errorMessage = createProperErrorMessage(ex.getMessage());
        redirectAttributes.addAttribute("Error", "Error: \n" +
                errorMessage);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    protected ResponseEntity<String> handleIllegalArgEx(IllegalArgumentException ex, RedirectAttributes redirectAttributes){
        String errorMessage = createProperErrorMessage(ex.getMessage());
        redirectAttributes.addAttribute("Error", "Error: \n" +
                errorMessage);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private String createProperErrorMessage(String message){
        return message.replaceAll("^\\s*\\d{3}\\s*\\w+\\s*\\w+:\\s*", "");
    }
}
