package com.hungry.dp.domain.portfolio.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.dto.request.FrameworkReq;
import com.hungry.dp.domain.portfolio.dto.request.LanguageReq;
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
    private final UserRepository userRepository;

    @Transactional
    public void uploadLanguage(LanguageReq languageReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorType.USER_NOT_FOUND));
        portfolio.addLanguages(languageReq.languages());
        ratingService.getCalculationResult(portfolio, user);
    }


    @Transactional
    public void save(User user) {
        Portfolio portfolio = Portfolio.from(user);
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void uploadFramework(FrameworkReq frameworkReq, String userId) {
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorType.PORTFOLIO_NOT_FOUND));
        portfolio.addFrameworks(frameworkReq.frameworks());
    }
}
