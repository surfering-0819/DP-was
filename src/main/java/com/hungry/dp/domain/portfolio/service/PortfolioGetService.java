package com.hungry.dp.domain.portfolio.service;

import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioGetService {
    private final PortfolioRepository portfolioRepository;

    public Optional<Portfolio> findByUserId(String userId) {
        return portfolioRepository.findByUserId(userId);
    }
}
