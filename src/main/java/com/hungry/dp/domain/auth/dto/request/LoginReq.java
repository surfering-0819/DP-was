package com.hungry.dp.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginReq (
        @NotBlank(message = "아이디는 필수입력사항")
        String identify,
        @NotBlank(message = "패스워드는 필수입력사항")
        String password
){
}
