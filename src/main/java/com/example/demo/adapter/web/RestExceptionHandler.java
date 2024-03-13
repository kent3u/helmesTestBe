package com.example.demo.adapter.web;

import com.example.demo.appdomain.ValidationException;
import com.example.demo.appdomain.client.NoSuchClientException;
import com.example.demo.appdomain.involvementapplication.NoInvolvementApplicationForClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        log.error("Unhandled exception", e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(NotFoundException e) {
        log.warn("Entity not found", e);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NoSuchClientException.class)
    public void handleNoSuchClientException(NoSuchClientException e) {
        log.warn("Client not found", e);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NoInvolvementApplicationForClientException.class)
    public void handleNoInvolvementApplicationForClientException(NoInvolvementApplicationForClientException e) {
        log.warn("Involvement application not found for client", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ValidationErrorsDto handleCalendarValidationException(ValidationException e) {
        log.info("Request produced validation errors: {}", e.getErrors(), e);
        return ValidationErrorsDto.of(e);
    }
}
