package kr.co.polycube.backendtest.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.domain.user.controller.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("NoHandlerFoundException 처리 테스트")
    public void noHandlerFoundExceptionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/notfound"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").value("요청하신 URL에 대한 Controller가 존재 하지 않습니다."));
    }

    @Test
    @DisplayName("MethodArgumentNotValidException 처리 테스트")
    public void validationExceptionTest() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(
                ""
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").value("Name 필드는 필수 입력 사항입니다."));
    }
}
