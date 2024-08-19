package com.hungry.dp.common.cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


@Component
public class CookieUtil {
    private static final int EXPIRATION = 86400000;

    //Rtk를 쿠키에 추가한다.
    public static ResponseCookie addRtkCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .maxAge(EXPIRATION)
                .secure(false)
                .httpOnly(true)
                .path("/")
                .build();
    }
}