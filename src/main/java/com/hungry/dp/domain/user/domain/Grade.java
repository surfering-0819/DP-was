package com.hungry.dp.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {
    NEWBIE(7), JUNIOR(15), SENIOR(25), PROFESSOR(40);
    private final int cutline;
}
