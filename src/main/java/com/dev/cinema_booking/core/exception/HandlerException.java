package com.dev.cinema_booking.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex , HttpServletRequest req){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status , "Validation failed for one or more fields");
        pd.setInstance(URI.create(req.getRequestURI()));

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String , String> errors = fieldErrors.stream()
                        .collect(Collectors.toMap(
                                FieldError::getField ,
                                e -> Objects.requireNonNullElse(e.getDefaultMessage(), "Validation failed") ,
                                (existingValue, newValue) -> existingValue
                        ));

        pd.setProperty("errors" , errors);
        pd.setProperty("timestamp" , Instant.now());
        return ResponseEntity.status(status).body(pd);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handlerBusiness(BusinessException ex , HttpServletRequest req){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status , ex.getMessage());
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("timestamp" , Instant.now());
        return ResponseEntity.status(status).body(pd);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handlerDataIntegrityViolation(
            DataIntegrityViolationException ex ,
            HttpServletRequest req
    ){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                status ,
                "Database constraint violation: Data already exists or invalid reference"
        );
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("timestamp" , Instant.now());
        return ResponseEntity.status(status).body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaughtException(
            Exception ex,
            HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal error occurred. Please try again later."
        );
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

}
