package kr.co.polycube.backendtest.domain.lotto.service.impl;

import kr.co.polycube.backendtest.domain.lotto.controller.dto.response.LottoNumberListResponse;
import kr.co.polycube.backendtest.domain.lotto.service.CreateLottoNumberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CreateLottoNumberServiceImpl implements CreateLottoNumberService {

    private static final Random random = new Random();

    @Override
    public LottoNumberListResponse createLottoNumberList() {

        Set<Integer> randomNumbers = new HashSet<>();
        do {
            randomNumbers.add(random.nextInt(45) + 1);
        } while (randomNumbers.size() != 6);

        return LottoNumberListResponse.of(new ArrayList<>(randomNumbers));
    }
}
