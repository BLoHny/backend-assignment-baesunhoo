package kr.co.polycube.backendtest.domain.lotto.controller;

import kr.co.polycube.backendtest.domain.lotto.controller.dto.response.LottoNumberListResponse;
import kr.co.polycube.backendtest.domain.lotto.service.CreateLottoNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lottos")
@RequiredArgsConstructor
public class LottoController {

    private final CreateLottoNumberService createLottoNumberService;

    @PostMapping
    public ResponseEntity<LottoNumberListResponse> createLottoNumber() {
        LottoNumberListResponse lottoNumberListResponse = createLottoNumberService.createLottoNumberList();
        return new ResponseEntity<>(lottoNumberListResponse, HttpStatus.CREATED);
    }
}
