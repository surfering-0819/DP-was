package com.hungry.dp.domain.user.domain;

import com.hungry.dp.domain.portfolio.domain.Portfolio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    private int weight = 0;

    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolios = new ArrayList<>();

    @Builder
    protected User(String identify, String password, String school, String job){
        this.identify = identify;
        this.password = password;
        this.school = school;
        this.job = Job.valueOf(job);
        this.grade = Grade.NEWBIE;
    }

    public void changeGrade(Grade newGrade) {
        this.grade = newGrade;
    }
}
