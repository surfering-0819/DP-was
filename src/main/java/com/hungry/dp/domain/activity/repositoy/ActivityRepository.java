package com.hungry.dp.domain.activity.repositoy;

import com.hungry.dp.domain.activity.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {
}
