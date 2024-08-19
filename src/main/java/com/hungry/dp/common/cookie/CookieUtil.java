package com.hungry.dp.common.cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


@Component
public class CookieUtil {
    @Value("${cookie.expiration}")
    private int EXPIRATION;

    //Rtk를 쿠키에 추가한다.
    public ResponseCookie addRtkCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .maxAge(EXPIRATION)
                .secure(false)
                .httpOnly(true)
                .path("/")
                .build();
    }
}