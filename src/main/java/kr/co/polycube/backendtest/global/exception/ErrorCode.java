package kr.co.polycube.backendtest.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("해당 Id에 대한 User 조회가 없습니다.", 400);

    private final String message;
    private final int status;
}