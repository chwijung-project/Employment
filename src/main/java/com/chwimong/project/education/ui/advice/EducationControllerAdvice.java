package com.chwimong.project.education.ui.advice;

import java.util.Collections;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.ui.view.ApiErrorView;
import com.chwimong.project.education.exception.MessageType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class EducationControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<?> clientAbortException(Exception ex) {
        return new ResponseEntity<>(new ApiErrorView(Collections.singletonList(MessageType.INTERNAL_SERVER_ERROR)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EducationException.class)
    public ResponseEntity<?> operationMessageException(EducationException ex) {
        return new ResponseEntity<>(new ApiErrorView(ex), ex.getStatus());
    }
}
