package com.hungry.dp.domain.portfolio.controller;

import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @PostMapping("/upload/language")
    public ResponseEntity<?> uploadLanguage(
            @RequestBody LanguageReq languageReq,
            @CookieValue String userId
    ){

    }
}
