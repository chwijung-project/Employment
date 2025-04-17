package com.chwimong.project.education.ui.view;

import com.chwimong.project.education.exception.MessageType;
import com.chwimong.project.employment.ui.common.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "meta", "data" })
public class ApiResponseView<T> {
    private final T data;
    private final MetaData meta;

    private ApiResponseView(T data, MetaData meta) {
        this.data = data;
        this.meta = meta;
    }

    public ApiResponseView(T data) {
        this.data = data;
        this.meta = null;
    }

    public static <T> ApiResponseView<T> of(MessageType messageType, T data, Page pageInfo) {
        return new ApiResponseView<>(
                data,
                new MetaData(messageType, pageInfo)
        );
    }

    public static <T> ApiResponseView<T> of(MessageType messageType, T data) {
        return new ApiResponseView<>(
            data,
            new MetaData(messageType, null)
        );
    }

    public static <T> ApiResponseView<T> of(MessageType messageType, T data, Page pageInfo, String customMessage) {
        return new ApiResponseView<>(
            data,
            new MetaData(messageType, pageInfo, customMessage)
        );
    }
    
    @Getter
    private static class MetaData {
        private final int status;
        private final String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final Page pageInfo;

        private MetaData(MessageType messageType, Page pageInfo) {
            this.status = messageType.getStatus().value();
            this.message = messageType.getMessage();
            this.pageInfo = pageInfo;
        }

        private MetaData(MessageType messageType, Page pageInfo, String customMessage) {
            this.status = messageType.getStatus().value();
            this.message = customMessage; 
            this.pageInfo = pageInfo;
        }
    }
}
