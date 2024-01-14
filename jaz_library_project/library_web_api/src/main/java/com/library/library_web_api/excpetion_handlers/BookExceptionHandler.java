package com.library.library_web_api.excpetion_handlers;

import com.library.library_web_api.webapi.exceptions.NotFoundException;
import com.library.library_web_api.webapi.services.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@RequiredArgsConstructor
public class BookExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BookExceptionHandler.class);
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleNotFoundEx(NotFoundException ex, RedirectAttributes redirectAttributes){
        String errorMessage = createProperErrorMessage(ex.getMessage());
        redirectAttributes.addAttribute("Error", "Error: \n" +
                errorMessage);
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    protected ResponseEntity<String> handleIllegalArgEx(IllegalArgumentException ex, RedirectAttributes redirectAttributes){
        String errorMessage = createProperErrorMessage(ex.getMessage());
        redirectAttributes.addAttribute("Error", "Error: \n" +
                errorMessage);
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private String createProperErrorMessage(String message){
        return message.replaceAll("^\\s*\\d{3}\\s*\\w+\\s*\\w+:\\s*", "");
    }
}
