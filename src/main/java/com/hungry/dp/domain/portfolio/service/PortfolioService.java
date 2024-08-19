package com.hungry.dp.domain.portfolio.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.activity.domain.Activity;
import com.hungry.dp.domain.activity.repositoy.ActivityRepository;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.dto.request.ActivityReq;
import com.hungry.dp.domain.portfolio.dto.request.ProjectReq;
import com.hungry.dp.domain.portfolio.dto.request.FrameworkReq;
import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
import com.hungry.dp.domain.portfolio.dto.response.PortfolioRes;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.project.domain.Project;
import com.hungry.dp.domain.project.repository.ProjectRepository;
import com.hungry.dp.domain.rating.service.RatingService;
import com.hungry.dp.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final RatingService ratingService;
    private final ProjectRepository projectRepository;
    private final ActivityRepository activityRepository;
    @Transactional
    public void save(User user) {
        Portfolio portfolio = Portfolio.from(user);
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void uploadLanguage(LanguageReq languageReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addLanguages(languageReq.languages());
        ratingService.CalculateLanguageWeight(portfolio, languageReq.languages(), userId);
    }

    @Transactional
    public void uploadFramework(FrameworkReq frameworkReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addFrameworks(frameworkReq.frameworks());
        ratingService.getCalculationResult(portfolio, userId, "Framework");
    }

    @Transactional
    public void uploadActivity(ActivityReq activityReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        Activity activity = ActivityReq.toEntity(activityReq);
        activityRepository.save(activity);
        portfolio.addActivity(activity);
        ratingService.getCalculationResult(portfolio, userId, "Activity");
    }
    @Transactional
    public void uploadProject(ProjectReq projectReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        Project project = ProjectReq.toEntity(projectReq);
        projectRepository.save(project);
        portfolio.addProject(project);
    }
    @Transactional(readOnly = true)
    public PortfolioRes get(String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.USER_NOT_FOUND));
        return PortfolioRes.fromEntity(portfolio);
    }
}
