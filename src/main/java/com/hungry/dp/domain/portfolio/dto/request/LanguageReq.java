package com.hungry.dp.domain.portfolio.dto.request;

import com.hungry.dp.domain.portfolio.domain.Language;

import java.util.List;

public record LanguageReq(
        List<Language> languages
){
}
