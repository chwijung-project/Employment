package com.chwimong.project.education.ui.view;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.exception.MessageType;

import lombok.Getter;
import lombok.ToString;

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

    public ApiErrorView(EducationException exception) {
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

        public static Error errorWithException(EducationException exception) {
            return new Error(exception.getType(), exception.getMessage());
        }

        private Error(String errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }
    }
}
