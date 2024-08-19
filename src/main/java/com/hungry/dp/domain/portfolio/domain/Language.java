package com.hungry.dp.domain.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    C(1), CP(2), CS(2), JAVA(5), PYTHON(5), JAVASCRIPT(4), TYPESCRIPT(5), R(1), GO(2), RUST(2), PHP(3), RUBY(2), DART(3);

    private final int weight;
}
