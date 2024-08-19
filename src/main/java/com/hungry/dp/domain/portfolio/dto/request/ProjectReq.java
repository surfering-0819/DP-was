package com.hungry.dp.domain.portfolio.dto.request;

import com.hungry.dp.domain.portfolio.domain.Framework;
import com.hungry.dp.domain.portfolio.domain.Language;
import com.hungry.dp.domain.project.domain.Project;

import java.util.List;

public record ProjectReq(
        String description,
        String content,
        List<Language> languages,
        List<Framework> frameworks
) {
    public static Project toEntity(ProjectReq projectReq){
        return Project.builder().description(projectReq.description()).content(projectReq.content()).build();
    }
}
