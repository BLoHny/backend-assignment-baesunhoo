package kr.co.polycube.backendtest.domain.lotto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.temporal.ValueRange;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LottoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST - /lottos | 로또 번호 생성 API success Test")
    void createLottoNumbersSuccessTest() throws Exception {
        ValueRange range = ValueRange.of(1, 45);

        mockMvc.perform(MockMvcRequestBuilders.post("/lottos"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numbers.length()").value(6))
                .andExpect(jsonPath("$.numbers[0]").isNumber())
                .andExpect(jsonPath("$.numbers[1]").isNumber())
                .andExpect(jsonPath("$.numbers[2]").isNumber())
                .andExpect(jsonPath("$.numbers[3]").isNumber())
                .andExpect(jsonPath("$.numbers[4]").isNumber())
                .andExpect(jsonPath("$.numbers[5]").isNumber());
    }
}
