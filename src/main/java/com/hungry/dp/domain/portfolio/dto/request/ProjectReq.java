package com.hungry.dp.domain.portfolio.dto.request;

import com.hungry.dp.domain.project.domain.Project;

import java.util.List;

public record ProjectReq(
        List<Project> projects
) {
}
