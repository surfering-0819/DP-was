package com.hungry.dp.common.response.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    UNAUTHORIZED("AUTH-001", 401, "접근 권한이 없습니다."),
    LOGIN_DENIED("AUTH-003", 401, "이메일이나 비밀번호가 일치하지 않습니다."),

    // 해당 값을 찾을 수 없을 때(404오류는 004)
    USER_NOT_FOUND("USER-004", 404, "존재하지 않는 회원입니다."),
    PORTFOLIO_NOT_FOUND("PORTFOLIO-004", 404, "존재하지 않는 포트폴리오입니다."),
    PROJECT_NOT_FOUND("PROJECT-004", 404, "존재하지 않는 프로젝트입니다."),

    INVALID_COMMENT_WRITER("COMMENT-001", 401, "댓글 작성자가 아닙니다."),
    INVALID_POST_WRITER("POST-001", 401, "게시글 작성자가 아닙니다."),

    // 서버 에러
    INTERNAL_SERVER_ERROR("SERVER-001", 500, "서버 에러 발생"),
    INVALID_ARGUMENT("VALID-001", 400, "데이터 전달 오류 발생");

    private final String errorCode;
    private final int status;
    private final String message;
}