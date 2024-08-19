package com.hungry.dp.common.response.dto;


import com.hungry.dp.common.response.type.ErrorType;

public record ErrorRes(String errorCode, int status, String message) {
    public static ErrorRes of(ErrorType errorType) {
        return new ErrorRes(errorType.getErrorCode(), errorType.getStatus(), errorType.getMessage());
    }
}
