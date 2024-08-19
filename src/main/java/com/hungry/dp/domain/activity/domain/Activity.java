package com.hungry.dp.domain.activity.domain;

import com.hungry.dp.domain.project.domain.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
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
    @Column(name = "participated_at")
    private LocalDateTime participatedAt;

    @NotNull
    private boolean is_award;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
