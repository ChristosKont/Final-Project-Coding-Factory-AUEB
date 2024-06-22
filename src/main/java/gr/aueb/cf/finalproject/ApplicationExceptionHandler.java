package gr.aueb.cf.finalproject;

import gr.aueb.cf.finalproject.dto.LoginMovieUserDTO;
import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.finalproject.service.exceptions.ErrorMessageResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import gr.aueb.cf.finalproject.service.exceptions.ErrorRestResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleRestResourceNotFoundException(RuntimeException ex) {
        ErrorRestResponse error = new ErrorRestResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleRestDataAccessException(EmptyResultDataAccessException ex) {
        ErrorRestResponse error = new ErrorRestResponse(List.of("Cannot delete non-existing resource"));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleRestDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorRestResponse error = new ErrorRestResponse(List.of("Data Integrity Violation: we cannot process your request."));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public String handleBadRequest(Model model, RuntimeException ex) {

        model.addAttribute("error", true);
        model.addAttribute("movieUser", new MovieUser());
        model.addAttribute("loginMovieUser", new LoginMovieUserDTO());

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(ex.getMessage());
        model.addAttribute("errorMsg", errorMessageResponse.toString());
        return "index";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public String handleBadCredentialException(Model model) {
        model.addAttribute("badCredential", true);
        model.addAttribute("movieUser", new MovieUser());
        model.addAttribute("loginMovieUser", new LoginMovieUserDTO());
        return "index";
    }
}
