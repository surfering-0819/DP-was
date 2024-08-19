package com.hungry.dp.common.response.dto;

public record SuccessRes<T>(T response) {
    public static <T> SuccessRes<T> from(T response) {
        return new SuccessRes<>(response);
    }
}