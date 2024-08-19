package com.hungry.dp.domain.portfolio.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.dto.request.ActivityReq;
import com.hungry.dp.domain.portfolio.dto.request.ProjectReq;
import com.hungry.dp.domain.portfolio.dto.request.FrameworkReq;
import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
import com.hungry.dp.domain.portfolio.dto.response.PortfolioRes;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.rating.service.RatingService;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final RatingService ratingService;

    @Transactional
    public void save(User user) {
        Portfolio portfolio = Portfolio.from(user);
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void uploadLanguage(LanguageReq languageReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addLanguages(languageReq.language());
        ratingService.getCalculationResult(portfolio, userId, "Language");
    }

    @Transactional
    public void uploadFramework(FrameworkReq frameworkReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addFrameworks(frameworkReq.framework());
        ratingService.getCalculationResult(portfolio, userId, "Framework");
    }

    @Transactional
    public void uploadActivity(ActivityReq activityReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addActivity(activityReq.activity());
        ratingService.getCalculationResult(portfolio, userId, "Activity");
    }

    @Transactional
    public void uploadProject(ProjectReq projectReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addProject(projectReq.projects());
        ratingService.getCalculationResult(portfolio, userId, "Project");
    }

    public PortfolioRes get(String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.USER_NOT_FOUND));
        return PortfolioRes.fromEntity(portfolio);
    }
}
