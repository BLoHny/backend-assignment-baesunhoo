package kr.co.polycube.backendtest.domain.user.controller;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.domain.user.controller.dto.request.EditInfoRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.request.UserRegisterRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserIdResponse;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserInfoResponse;
import kr.co.polycube.backendtest.domain.user.service.EditUserInfoService;
import kr.co.polycube.backendtest.domain.user.service.GetUserInfoService;
import kr.co.polycube.backendtest.domain.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRegisterService userRegisterService;
    private final GetUserInfoService getUserInfoService;
    private final EditUserInfoService editUserInfoService;

    @PostMapping
    public ResponseEntity<UserIdResponse> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        UserIdResponse userIdResponse = userRegisterService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(userIdResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Long id) {
        UserInfoResponse userInfoResponse = getUserInfoService.getUserInfo(id);
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserInfoResponse> editUserInfo(@PathVariable Long id, @RequestBody @Valid EditInfoRequest editInfoRequest) {
        UserInfoResponse userInfoResponse = editUserInfoService.editUserInfoService(id, editInfoRequest);
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }
}
