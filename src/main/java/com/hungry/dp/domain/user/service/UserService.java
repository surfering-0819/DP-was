package com.hungry.dp.domain.user.service;

import com.hungry.dp.domain.user.UserRepository;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.dto.request.SignUpReq;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignUpReq signUpReq) {
        String password = passwordEncoder.encode(signUpReq.password());
        User user = SignUpReq.fromEntity(signUpReq, password);
        this.save(user);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> findByIdentify(String identify) {
        return userRepository.findByIdentify(identify);
    }
}
