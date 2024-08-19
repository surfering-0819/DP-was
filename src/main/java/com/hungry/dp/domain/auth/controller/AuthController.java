package com.hungry.dp.domain.auth.controller;

import com.hungry.dp.common.cookie.CookieUtil;
import com.hungry.dp.domain.auth.dto.request.LoginReq;
import com.hungry.dp.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CookieUtil cookieUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq){
        String identify = authService.login(loginReq);
        return ResponseEntity.ok().header("Set-Cookie",
                        cookieUtil.addRtkCookie("refreshToken", identify).toString()).build();
    }
}
