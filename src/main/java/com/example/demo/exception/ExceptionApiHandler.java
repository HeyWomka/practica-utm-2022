package com.example.demo.exception;

import com.example.demo.exception.custom.ClientNotCreatedException;
import com.example.demo.exception.custom.ClientNotFoundException;
import com.example.demo.exception.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

  @ExceptionHandler(ClientNotFoundException.class)
  public ResponseEntity<ErrorMessage> clientNotFoundException(ClientNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(ClientNotCreatedException.class)
  public static ResponseEntity<ErrorMessage> clientNotCreatedException(ClientNotCreatedException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage(exception.getMessage()));
  }
}
