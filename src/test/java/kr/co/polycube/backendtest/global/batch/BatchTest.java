package kr.co.polycube.backendtest.global.batch;

import kr.co.polycube.backendtest.domain.lotto.entity.Lotto;
import kr.co.polycube.backendtest.domain.lotto.entity.Winner;
import kr.co.polycube.backendtest.domain.lotto.repository.LottoRepository;
import kr.co.polycube.backendtest.domain.lotto.repository.WinnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
class BatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    @Test
    void lottoBatchProcessTest() throws Exception {
        Lotto lotto1 = Lotto.builder()
                .number1(1)
                .number2(2)
                .number3(3)
                .number4(4)
                .number5(5)
                .number6(6)
                .build();

        Lotto lotto2 = Lotto.builder()
                .number1(10)
                .number2(20)
                .number3(30)
                .number4(40)
                .number5(1)
                .number6(2)
                .build();

        lottoRepository.saveAll(Arrays.asList(lotto1, lotto2));

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);

        List<Winner> winners = winnerRepository.findAll();
        assertThat(winners).isNotEmpty();
        assertThat(winners).allMatch(winner -> winner.getRanking() > 0 && winner.getRanking() <= 5);
    }
}
