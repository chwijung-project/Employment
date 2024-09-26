package com.chwimong.project.employment.ui.view;

import com.chwimong.project.employment.exception.ChwimongException;
import com.chwimong.project.employment.exception.MessageType;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ApiErrorView {
    private final List<Error> errors;

    public ApiErrorView(List<MessageType> messageTypes) {
        this.errors = messageTypes.stream().map(Error::errorWithMessageType).collect(Collectors.toList());
    }
    public ApiErrorView(MessageType messageType) {
        this.errors = Collections.singletonList(Error.errorWithMessageType(messageType));
    }

    public ApiErrorView(ChwimongException exception) {
        this.errors = Collections.singletonList(Error.errorWithException(exception));
    }

    @Getter
    @ToString
    public static class Error {
        private final String errorType;
        private final String errorMessage;

        public static Error errorWithMessageType(MessageType messageType) {
            return new Error(messageType.name(), messageType.getMessage());
        }

        public static Error errorWithException(ChwimongException exception) {
            return new Error(exception.getType(), exception.getMessage());
        }

        private Error(String errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }
    }
}
