package com.hungry.dp.domain.project.domain;

import com.hungry.dp.domain.portfolio.domain.Portfolio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private String content;

    @Builder
    protected Project(String description, String content){
        this.description = description;
        this.content = content;
    }

    public void addPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        this.portfolio.getProjects().add(this);
    }
}
