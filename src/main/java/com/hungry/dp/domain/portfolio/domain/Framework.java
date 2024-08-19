package com.hungry.dp.domain.portfolio.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Framework {
    REACT(5), VUE(3), ANGULAR(1), DJANGO(3), FLASK(3), RUBY_ON_RAILS(1), SPRINGBOOT(5), EXPRESS(2), NEST(3), LARABEL(2), FLUTTER(3), REACT_NATIVE(4), SWIFT(3), UNITY(4), UNREAL(5), TENSORFLOW(4), PYTORCH(5), SCIKIT_LEARN(3);

    private final Integer weight;
}
