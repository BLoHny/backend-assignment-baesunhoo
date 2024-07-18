package kr.co.polycube.backendtest.domain.user.service;

import kr.co.polycube.backendtest.domain.user.controller.dto.request.EditInfoRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserInfoResponse;

public interface EditUserInfoService {

    UserInfoResponse editUserInfoService(Long userId, EditInfoRequest editInfoRequest);
}
