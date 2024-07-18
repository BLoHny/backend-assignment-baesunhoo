package kr.co.polycube.backendtest.domain.user.service;

import kr.co.polycube.backendtest.domain.user.controller.dto.request.UserRegisterRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserIdResponse;

public interface UserRegisterService {

    UserIdResponse registerUser(UserRegisterRequest userRegisterRequest);
}
