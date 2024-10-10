package com.chwimong.project.employment.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ChwimongException extends RuntimeException{
    private final HttpStatus status;
    private final String type;

    public ChwimongException(MessageType message) {
        super(message.getMessage());
        this.status = message.getStatus();
        this.type = message.name();
    }
}
