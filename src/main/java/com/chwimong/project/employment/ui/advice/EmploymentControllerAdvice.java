package com.chwimong.project.employment.ui.advice;

import com.chwimong.project.employment.exception.EmploymentException;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.view.ApiErrorView;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class EmploymentControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<?> clientAbortException(Exception ex) {
        return new ResponseEntity<>(new ApiErrorView(Collections.singletonList(MessageType.INTERNAL_SERVER_ERROR)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmploymentException.class)
    public ResponseEntity<?> operationMessageException(EmploymentException ex) {
        return new ResponseEntity<>(new ApiErrorView(ex), ex.getStatus());
    }
}
