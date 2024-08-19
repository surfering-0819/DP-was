package com.hungry.dp.domain.project.repository;

import com.hungry.dp.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String>{
}
