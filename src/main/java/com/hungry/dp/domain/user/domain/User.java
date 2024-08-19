package com.hungry.dp.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(unique = true)
    private String identify;

    @NotNull
    @Column(unique = true)
    private String password;

    @NotNull
    private String school;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Job job;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Grade grade;

    private int weight;
    @Builder
    protected User(String identify, String password, String school, String job){
        this.identify = identify;
        this.password = password;
        this.school = school;
        this.job = Job.valueOf(job);
        this.grade = Grade.NEWBIE;
    }
}
