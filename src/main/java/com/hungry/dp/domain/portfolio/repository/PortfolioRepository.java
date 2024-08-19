package com.hungry.dp.domain.portfolio.repository;

import com.hungry.dp.domain.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
    Optional<Portfolio> findByUserId(String userId);
}
