package com.chwimong.project.education.exception;

import org.springframework.http.HttpStatus;

import com.chwimong.project.employment.exception.MessageType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EducationException extends RuntimeException{
	private static final long serialVersionUID = 4830719456759247700L;
	
	private final HttpStatus status;
    private final String type;

    public EducationException(MessageType message) {
        super(message.getMessage());
        this.status = message.getStatus();
        this.type = message.name();
    }
}