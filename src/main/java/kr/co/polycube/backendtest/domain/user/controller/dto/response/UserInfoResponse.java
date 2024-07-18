package kr.co.polycube.backendtest.domain.user.controller.dto.response;

public record UserInfoResponse(
        long id,
        String name
) {

    public static UserInfoResponse of(Long id, String name) {
        return new UserInfoResponse(id, name);
    }
}
