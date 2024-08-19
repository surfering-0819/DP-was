package com.hungry.dp.domain.rating.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Language;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.rating.dto.RatingRes;
import com.hungry.dp.domain.user.domain.Grade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final PortfolioRepository portfolioRepository;

    public RatingRes getCalculationResult(UUID portfolioId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(() -> new CustomException(ErrorType.POST_NOT_FOUND));

        // 기존 등급
        Grade prevGrade = portfolio.getUser().getGrade();

        // 새로운 가중치 계산
        int weight = portfolio.getLanguages().stream()
                .map(Language::getWeight) // 각 Language 객체의 weight 값을 가져옴
                .reduce(0, Integer::sum); // weight 값을 합산

        Grade newGrade = measureGrade(weight);

        if (prevGrade.getCutline() <= newGrade.getCutline())
            return RatingRes.toDto(weight, prevGrade, false);
        return RatingRes.toDto(weight, newGrade, true);
    }

    private Grade measureGrade(int weight) {
        if (weight < Grade.NEWBIE.getCutline()) return Grade.NEWBIE;
        if (weight < Grade.JUNIOR.getCutline()) return Grade.JUNIOR;
        if (weight < Grade.SENIOR.getCutline()) return Grade.SENIOR;
        return Grade.PROFESSOR;
    }
}
