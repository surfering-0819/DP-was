package com.hungry.dp.domain.portfolio.dto.request;

import com.hungry.dp.domain.portfolio.domain.Framework;

import java.util.List;

public record FrameworkReq (
        List<Framework> frameworks
){
}
