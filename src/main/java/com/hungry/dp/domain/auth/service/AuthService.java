package com.hungry.dp.domain.auth.service;

import com.hungry.dp.common.encode.PasswordUtil;
import com.hungry.dp.common.exception.CustomException;
import com.hungry.dp.common.response.type.ErrorType;
import com.hungry.dp.domain.auth.dto.request.LoginReq;
import com.hungry.dp.domain.user.domain.User;
import com.hungry.dp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    @Transactional(readOnly = true)
    public String login(LoginReq loginReq) {
        User user = this.validateUser(loginReq);
        return user.getIdentify();
    }

    private User validateUser(LoginReq loginReq) {
        User user = userService.findByIdentify(loginReq.identify()).orElseThrow(()->new CustomException(ErrorType.LOGIN_DENIED));

        if(!PasswordUtil.checkPassword(loginReq.password(), user.getPassword()))
            throw new CustomException(ErrorType.LOGIN_DENIED);
        return user;
    }
}
