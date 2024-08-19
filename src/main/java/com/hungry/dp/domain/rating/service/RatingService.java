package com.hungry.dp.domain.rating.service;

import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.portfolio.domain.Language;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import com.hungry.dp.domain.user.domain.Grade;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.repository.UserRepository;
import com.hungry.dp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final UserRepository userRepository;

    public void CalculateLanguageWeight(Portfolio portfolio, List<Language> languages, String userId) {
        Grade prevGrade = portfolio.getUser().getGrade();
        int sum = languages.stream()
                    .map(Language::getWeight) // 각 Language 객체의 weight 값을 가져옴
                    .reduce(0, Integer::sum);
        getTotalWeight(userId, sum, prevGrade);
    }

    public void getTotalWeight(String userId, int sum, Grade prevGrade){
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorType.USER_NOT_FOUND));
        user.changeWeight(sum);

        Grade newGrade = measureGrade(user.getWeight());

        if (prevGrade.getCutline() >= newGrade.getCutline()) {
            user.changeGrade(newGrade);
            return RatingRes.from(newGrade, false);
        }
        user.changeGrade(newGrade);
        return RatingRes.from(newGrade, true);
    }

    private Grade measureGrade(int weight) {
        if (weight < Grade.NEWBIE.getCutline()) return Grade.NEWBIE;
        if (weight < Grade.JUNIOR.getCutline()) return Grade.JUNIOR;
        if (weight < Grade.SENIOR.getCutline()) return Grade.SENIOR;
        return Grade.PROFESSOR;
    }
}
