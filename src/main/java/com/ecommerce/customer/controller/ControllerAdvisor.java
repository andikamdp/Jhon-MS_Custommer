package com.ecommerce.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleMethodNoSuchElementException(HttpServletRequest httpRequest, NoSuchElementException ex) {

        log.error(
                httpRequest.getServletPath() + " | " + httpRequest.getMethod() + " | " + ex.getMessage());

        return ex.getMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleMethodNoSuchElementException(HttpServletRequest httpRequest, MethodArgumentNotValidException ex) {

        log.error(
                httpRequest.getServletPath() + " | " + httpRequest.getMethod() + " | " + ex.getMessage());

        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleMethodDataIntegrityViolationException(HttpServletRequest httpRequest, DataIntegrityViolationException ex) {

        log.error(
                httpRequest.getServletPath() + " | " + httpRequest.getMethod() + " | " + ex.getMessage());

        return ex.getMostSpecificCause().getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleMethodArgumentNotValid(HttpServletRequest httpRequest, Exception ex) {

        log.error(
                httpRequest.getServletPath() + " | " + httpRequest.getMethod() + " | " + ex.getMessage());

        return "System unavailable";
    }

}
