package kr.co.polycube.backendtest.global.batch;

import kr.co.polycube.backendtest.domain.lotto.entity.Lotto;
import kr.co.polycube.backendtest.domain.lotto.entity.Winner;
import kr.co.polycube.backendtest.domain.lotto.repository.LottoRepository;
import kr.co.polycube.backendtest.domain.lotto.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class LottoBatchProcess {

    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    public void execute() {
        final List<Integer> winningNumbers = generateWinningNumbers();
        List<Lotto> lottoList = lottoRepository.findAll();

        for (Lotto lotto : lottoList) {
            List<Integer> lottoNumbers = convertList(lotto);
            lottoNumbers.removeAll(winningNumbers);
            int rank = calculateRank(lottoNumbers.size());

            if (rank != -1) {
                Winner winner = Winner.builder()
                        .lottoId(lotto.getId())
                        .ranking(rank)
                        .build();
                winnerRepository.save(winner);
            }
        }
    }

    private List<Integer> convertList(Lotto lotto) {
        return new ArrayList<>(Arrays.asList(
                lotto.getNumber1(),
                lotto.getNumber2(),
                lotto.getNumber3(),
                lotto.getNumber4(),
                lotto.getNumber5(),
                lotto.getNumber6()
        ));
    }

    private List<Integer> generateWinningNumbers() {
        Random random = new Random();
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < 6) {
            lottoNumbers.add(random.nextInt(45) + 1);
        }

        return new ArrayList<>(lottoNumbers);
    }

    private int calculateRank(int count) {
        return switch (count - 1) {
            case 0 -> LottoRank.FIRST.getRank();
            case 1 -> LottoRank.SECOND.getRank();
            case 2 -> LottoRank.THIRD.getRank();
            case 3 -> LottoRank.FOUR.getRank();
            case 4 -> LottoRank.FIVE.getRank();
            default -> LottoRank.NOTHING.getRank();
        };
    }
}
