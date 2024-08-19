package com.hungry.dp.domain.portfolio.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.dto.request.ActivityReq;
import com.hungry.dp.domain.portfolio.dto.request.FrameworkReq;
import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
import com.hungry.dp.domain.portfolio.dto.request.ProjectReq;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.rating.service.RatingService;
import com.hungry.dp.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioGetService portfolioGetService;
    private final PortfolioRepository portfolioRepository;
    private final RatingService ratingService;

    public void save(User user) {
        Portfolio portfolio = Portfolio.from(user);
        portfolioRepository.save(portfolio);
    }

    private Portfolio getPortfolio(String userId) {
        return portfolioGetService.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
    }

    public void uploadLanguage(LanguageReq languageReq, String userId) {
        Portfolio portfolio = getPortfolio(userId);
        portfolio.addLanguages(languageReq.languages());
        ratingService.getCalculationResult(portfolio, userId, "Language");
    }

    public void uploadFramework(FrameworkReq frameworkReq, String userId) {
        Portfolio portfolio = getPortfolio(userId);
        portfolio.addFrameworks(frameworkReq.frameworks());
        ratingService.getCalculationResult(portfolio, userId, "Framework");
    }

    public void uploadActivity(ActivityReq activityReq, String userId) {
        Portfolio portfolio = getPortfolio(userId);
        portfolio.addActivity(activityReq.activities());
        ratingService.getCalculationResult(portfolio, userId, "Activity");
    }

    public void uploadProject(ProjectReq projectReq, String userId) {
        Portfolio portfolio = getPortfolio(userId);

    }

}
