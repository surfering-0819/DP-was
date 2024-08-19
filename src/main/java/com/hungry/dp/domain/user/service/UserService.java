package com.hungry.dp.domain.user.service;
import org.springframework.transaction.annotation.Transactional;
import com.hungry.dp.common.encode.PasswordUtil;
import com.hungry.dp.domain.portfolio.service.PortfolioService;
import com.hungry.dp.domain.user.repository.UserRepository;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.dto.request.SignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PortfolioService portfolioService;

    @Transactional
    public void signup(SignUpReq signUpReq) {
        log.info("회원가입 실행");
        String password = PasswordUtil.hashPassword(signUpReq.password());
        User user = SignUpReq.fromEntity(signUpReq, password);
        portfolioService.save(user);
        this.save(user);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByIdentify(String identify) {
        return userRepository.findByIdentify(identify);
    }

}
