package com.hungry.dp.domain.user.controller;

import com.hungry.dp.common.response.dto.SuccessRes;
import com.hungry.dp.domain.user.dto.request.SignUpReq;
import com.hungry.dp.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpReq signUpReq){
        userService.signup(signUpReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessRes.from("회원가입 성공"));
    }
}
