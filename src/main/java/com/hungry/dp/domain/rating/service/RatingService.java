package com.hungry.dp.domain.rating.service;

import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private PortfolioRepository portfolioRepository;

    public void getCalculationResult() {}
}
