package kr.co.polycube.backendtest.domain.lotto.controller.dto.response;

import java.util.List;

public record LottoNumberListResponse(
        List<Integer> numbers
) {

    public static LottoNumberListResponse of(List<Integer> lottoNumbers) {
        return new LottoNumberListResponse(lottoNumbers);
    }
}
