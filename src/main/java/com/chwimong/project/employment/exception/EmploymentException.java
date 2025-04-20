package com.chwimong.project.employment.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class EmploymentException extends RuntimeException{
	private static final long serialVersionUID = 4830719456759247700L;
	
	private final HttpStatus status;
    private final String type;

    public EmploymentException(MessageType message) {
        super(message.getMessage());
        this.status = message.getStatus();
        this.type = message.name();
    }
}
