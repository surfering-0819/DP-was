package com.hungry.dp.domain.rating.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Language;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.user.domain.Grade;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {


    @Transactional
    public void getCalculationResult(Portfolio portfolio, User user) {
        // 기존 등급
        Grade prevGrade = portfolio.getUser().getGrade();

        // 새로운 가중치 계산
        int weight = portfolio.getLanguages().stream()
                .map(Language::getWeight) // 각 Language 객체의 weight 값을 가져옴
                .reduce(0, Integer::sum); // weight 값을 합산

        Grade newGrade = measureGrade(weight);

        if (prevGrade.getCutline() > newGrade.getCutline()) {
            user.changeGrade(newGrade);
        }
        user.changeGrade(newGrade);
    }

    private Grade measureGrade(int weight) {
        if (weight < Grade.NEWBIE.getCutline()) return Grade.NEWBIE;
        if (weight < Grade.JUNIOR.getCutline()) return Grade.JUNIOR;
        if (weight < Grade.SENIOR.getCutline()) return Grade.SENIOR;
        return Grade.PROFESSOR;
    }
}
