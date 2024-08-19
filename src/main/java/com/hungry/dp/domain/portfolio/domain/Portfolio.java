package com.hungry.dp.domain.portfolio.domain;

import com.hungry.dp.common.domain.BaseEntity;
import com.hungry.dp.domain.activity.domain.Activity;
import com.hungry.dp.domain.project.domain.Project;
import com.hungry.dp.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ElementCollection
    private List<Language> languages = new ArrayList<>();

    @ElementCollection
    private List<Framework> frameworks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "portfolio")
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio")
    private List<Project> projects = new ArrayList<>();

    @Builder
    protected Portfolio(User user){
        this.user = user;
    }

    public static Portfolio from(User user){
        return Portfolio.builder().user(user).build();
    }

    public void addLanguages(List<Language> languages) {
        this.languages.addAll(languages);
    }

    public void addFrameworks(List<Framework> frameworks) {
        this.frameworks.addAll(frameworks);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

}
