package kr.co.polycube.backendtest.global.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("URL에 부적절한 요소가 있는 경우 필터 차단 테스트")
    public void invalidUrlTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}?name=test!!", 1L)
                        .param("param1", "value1")
                        .param("param2", "value2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").value("URL에 부적절한 요소가 감지 되었습니다."));
    }
}
