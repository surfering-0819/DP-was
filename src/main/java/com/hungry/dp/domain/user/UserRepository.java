package com.hungry.dp.domain.user;

import com.hungry.dp.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdentify(String identify);
}
