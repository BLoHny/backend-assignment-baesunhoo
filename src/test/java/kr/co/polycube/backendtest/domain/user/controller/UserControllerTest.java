package kr.co.polycube.backendtest.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.domain.user.controller.dto.request.EditInfoRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.request.UserRegisterRequest;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserIdResponse;
import kr.co.polycube.backendtest.domain.user.controller.dto.response.UserInfoResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(4)
    @DisplayName("POST - /users | 유저 생성 성공 테스트")
    public void registerUserSuccessTest() throws Exception {
        UserIdResponse userIdResponse = new UserIdResponse(1L);
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("tester");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userIdResponse.id()));
    }

    @Test
    @Order(1)
    @DisplayName("POST - /users | 유저 생성 실패 테스트")
    public void registerUserFailedTest() throws Exception {
        UserRegisterRequest nullRequest = new UserRegisterRequest(null);
        UserRegisterRequest blankRequest = new UserRegisterRequest("");

        for (Object jsonData : new Object[]{nullRequest, blankRequest}) {
            mockMvc.perform(MockMvcRequestBuilders.post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(jsonData)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    @Order(5)
    @DisplayName("GET - /users/{id} | 유저 정보 가져오기 성공 테스트")
    public void getUserInfoSuccessTest() throws Exception {
        UserInfoResponse userInfoResponse = new UserInfoResponse(1L, "tester");

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userInfoResponse.id()))
                .andExpect(jsonPath("$.name").value(userInfoResponse.name()));
    }

    @Test
    @Order(2)
    @DisplayName("GET - /users/{id} | 유저 정보 가져오기 실패 테스트")
    public void getUserInfoFailedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 100L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    @DisplayName("PATCH - /users/{id} | 유저 정보 수정 성공 테스트")
    public void editUserInfoSuccessTest() throws Exception {
        UserInfoResponse userInfoResponse = new UserInfoResponse(1L, "newUsername");
        EditInfoRequest editInfoRequest = new EditInfoRequest("newUsername");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editInfoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userInfoResponse.id()))
                .andExpect(jsonPath("$.name").value(userInfoResponse.name()));
    }

    @Test
    @Order(3)
    @DisplayName("PATCH - /users/{id} | 유저 정보 수정 실패 테스트")
    public void editUserInfoFailedTest() throws Exception {
        EditInfoRequest nullRequest = new EditInfoRequest(null);
        EditInfoRequest blankRequest = new EditInfoRequest("");

        for (Object jsonData : new Object[]{nullRequest, blankRequest}) {
            mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(jsonData)))
                    .andExpect(status().isBadRequest());
        }
    }
}
