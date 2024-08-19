package com.hungry.dp.domain.rating.dto;

import com.hungry.dp.domain.user.domain.Grade;

public record RatingRes(
        int weight,
        Grade grade,
        boolean gradeUp
) {
    public static RatingRes toDto(int weight, Grade grade, boolean gradeUp) {
        return new RatingRes(weight, grade, gradeUp);
    }
}
