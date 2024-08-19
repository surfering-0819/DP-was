package com.hungry.dp.domain.rating.dto.response;

import com.hungry.dp.domain.user.domain.Grade;

public record RatingRes(
        Grade grade,
        boolean isUpgrade
) {
    public static RatingRes from(Grade grade, boolean isUpgrade) {
        return new RatingRes(grade, isUpgrade);
    }
}
