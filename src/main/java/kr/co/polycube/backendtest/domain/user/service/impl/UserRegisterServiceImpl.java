package kr.co.polycube.backendtest.domain.user.service.impl;

import kr.co.polycube.backendtest.domain.user.controller.dto.request.UserRegisterRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserIdResponse;
import kr.co.polycube.backendtest.domain.user.entity.User;
import kr.co.polycube.backendtest.domain.user.repository.UserRepository;
import kr.co.polycube.backendtest.domain.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserIdResponse registerUser(UserRegisterRequest userRegisterRequest) {

        User user = User.builder()
                .name(userRegisterRequest.getName())
                .build();
        userRepository.save(user);

        return UserIdResponse.of(user.getId());
    }
}
