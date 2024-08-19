package com.hungry.dp.common.exception;

import com.hungry.dp.common.response.dto.ErrorRes;
import com.hungry.dp.common.response.type.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.hungry.dp.common.response.type.ErrorType.INTERNAL_SERVER_ERROR;
import static com.hungry.dp.common.response.type.ErrorType.INVALID_ARGUMENT;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handle(CustomException ex){
        ErrorType errorType = ex.getErrortype();
        log.error("Error occurred : [errorCode={}, status={}, message={}]", errorType.getErrorCode(), errorType.getStatus(), errorType.getMessage());
        return ResponseEntity.status(errorType.getStatus()).body(ErrorRes.of(errorType));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            errors.put("errorCode", INVALID_ARGUMENT.getErrorCode());
            errors.put(error.getField(), error.getDefaultMessage());
        }
        log.error("Error occurred : [errorCode={}, message={}]", INVALID_ARGUMENT.getErrorCode(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<?> customServerException(Exception ex) {
//        ErrorRes error = new ErrorRes(INTERNAL_SERVER_ERROR.getErrorCode(), INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage());
//        log.error("Error occurred : [errorCode={}, status={}, message={}]", error.errorCode(), error.status(), error.message());
//        return ResponseEntity.status(error.status()).body(error);
//    }
}