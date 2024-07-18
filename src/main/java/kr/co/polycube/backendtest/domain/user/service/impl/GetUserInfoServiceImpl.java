package kr.co.polycube.backendtest.domain.user.service.impl;

import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserInfoResponse;
import kr.co.polycube.backendtest.domain.user.entity.User;
import kr.co.polycube.backendtest.domain.user.repository.UserRepository;
import kr.co.polycube.backendtest.domain.user.service.GetUserInfoService;
import kr.co.polycube.backendtest.global.exception.BasicException;
import kr.co.polycube.backendtest.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserInfoServiceImpl implements GetUserInfoService {

    private final UserRepository userRepository;

    @Override
    public UserInfoResponse getUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BasicException(ErrorCode.USER_NOT_FOUND));
        return UserInfoResponse.of(user.getId(), user.getName());
    }
}
