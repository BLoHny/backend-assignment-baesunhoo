package kr.co.polycube.backendtest.domain.user.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditInfoRequest {

    @NotBlank(message = "Name 필드는 필수 입력 사항입니다.")
    private String name;
}