package com.hungry.dp.domain.portfolio.dto.request;

import com.hungry.dp.domain.activity.domain.Activity;

import java.time.LocalDateTime;

public record ActivityReq(
        String organizer,
        String title,

        String content,

        LocalDateTime participatedAt,
        boolean isAward
) {
    public static Activity toEntity(ActivityReq activityReq){
        return Activity.builder()
                .title(activityReq.title())
                .organizer(activityReq.organizer())
                .content(activityReq.content())
                .participatedAt(activityReq.participatedAt())
                .isAward(activityReq.isAward())
                .build();
    }
}
