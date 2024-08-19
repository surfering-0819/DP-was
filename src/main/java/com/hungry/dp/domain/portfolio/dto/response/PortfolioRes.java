package com.hungry.dp.domain.portfolio.dto.response;

import com.hungry.dp.domain.activity.domain.Activity;
import com.hungry.dp.domain.portfolio.domain.Framework;
import com.hungry.dp.domain.portfolio.domain.Language;
import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.project.domain.Project;

import java.util.List;

public record PortfolioRes(
        List<Language> languages,
        List<Framework> frameworks,
        List<Project> projects,
        List<Activity> activities,
        String name,
        String school
) {
    public static PortfolioRes fromEntity(Portfolio portfolio){
        return new PortfolioRes(portfolio.getLanguages(), portfolio.getFrameworks(), portfolio.getProjects(),
                portfolio.getActivities(), portfolio.getUser().getName(), portfolio.getUser().getSchool());
    }
}
