package com.chwimong.project.employment.ui.view;

import com.chwimong.project.config.Page;
import com.chwimong.project.employment.exception.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "data", "pageInfo" })
public class ApiResponseView<T> {
    private final T data;
    private final Page pageInfo;

    @JsonIgnore
    private final MessageType messageType;

    private ApiResponseView(MessageType messageType, T data, Page pageInfo) {
        this.messageType = messageType;
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public static <T> ApiResponseView<T> of(MessageType messageType, T data, Page pageInfo) {
        return new ApiResponseView<>(messageType, data, pageInfo);
    }

    public int getStatus() {
        return messageType.getStatus().value();
    }

    public String getMessage() {
        return messageType.getMessage();
    }
}
