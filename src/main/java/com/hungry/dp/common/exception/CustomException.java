package com.hungry.dp.common.exception;

import com.hungry.dp.common.response.type.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorType errortype;
}
