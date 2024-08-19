package com.hungry.dp.domain.portfolio.controller;

import com.hungry.dp.common.response.dto.SuccessRes;
import com.hungry.dp.domain.portfolio.dto.request.ActivityReq;
import com.hungry.dp.domain.portfolio.dto.request.FrameworkReq;
import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
import com.hungry.dp.domain.portfolio.dto.request.ProjectReq;
import com.hungry.dp.domain.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;
    @PostMapping("/upload/language")

    public ResponseEntity<?> uploadLanguage(
            @RequestBody LanguageReq languageReq,
            @CookieValue String userId
    ){
        portfolioService.uploadLanguage(languageReq, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessRes.from("언어 추가 완료"));
    }

    @PostMapping("/upload/framework")
    public ResponseEntity<?> uploadFramework(
            @RequestBody FrameworkReq frameworkReq,
            @CookieValue String userId
    ){
        portfolioService.uploadFramework(frameworkReq, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessRes.from("프레임워크 추가 완료"));
    }

    @PostMapping("/upload/activity")
    public ResponseEntity<?> uploadActivity(
            @RequestBody ActivityReq activityReq,
            @CookieValue String userId
    ) {
        portfolioService.uploadActivity(activityReq, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessRes.from("대외활동 추가 완료"));
    }

    @PostMapping("/upload/project")
    public ResponseEntity<?> uploadProject(
            @RequestBody ProjectReq projectReq,
            @CookieValue String userId
    ) {
        portfolioService.uploadProject(projectReq, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessRes.from(("프로젝트 추가 완료")));
    }
}
