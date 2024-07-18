package kr.co.polycube.backendtest.domain.user.controller.dto.response;

public record UserIdResponse(
        Long id
) {

    public static UserIdResponse of(Long id) {
        return new UserIdResponse(id);
    }
}
