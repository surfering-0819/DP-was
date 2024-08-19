package com.hungry.dp.domain.user.dto.request;

import com.hungry.dp.domain.user.domain.Grade;
import com.hungry.dp.domain.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public record SignUpReq(
        @NotBlank(message = "아이디는 필수입력입니다.")
        String identify,
        @NotBlank(message = "비밀번호는 필수입력입니다.")
        String password,
        @NotBlank(message = "학교는 필수입력입니다.")
        String school,
        @NotBlank(message = "직종은 필수입력입니다.")
        String job,

        @NotBlank(message = "이름은 필수입력입니다.")
        String name
        ){
    public static User fromEntity(SignUpReq signUpReq, String password) {
        return User.builder()
                .identify(signUpReq.identify())
                .password(password)
                .school(signUpReq.school())
                .job(signUpReq.job())
                .name(signUpReq.name())
                .grade(Grade.NEWBIE)
                .build();
    }
}
