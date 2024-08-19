package com.hungry.dp.domain.activity.domain;

import com.hungry.dp.domain.portfolio.domain.Portfolio;
import com.hungry.dp.domain.project.domain.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String organizer;

    @NotNull
    private String content;

    @NotNull
    @Column(name = "participated_at")
    private LocalDateTime participatedAt;

    @NotNull
    @Column(name = "is_award")
    private boolean isAward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Builder
    protected Activity(String title, String organizer, String content, LocalDateTime participatedAt, boolean isAward){
        this.title = title;
        this.organizer = organizer;
        this.content = content;
        this.participatedAt = participatedAt;
        this.isAward = isAward;
    }
}
