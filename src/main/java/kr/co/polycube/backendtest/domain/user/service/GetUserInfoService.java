package kr.co.polycube.backendtest.domain.user.service;

import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserInfoResponse;

public interface GetUserInfoService {

    UserInfoResponse getUserInfo(Long userId);
}
