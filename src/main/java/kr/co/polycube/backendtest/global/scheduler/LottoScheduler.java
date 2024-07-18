package kr.co.polycube.backendtest.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class LottoScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Scheduled(cron = "0 0 0 * * SUN")
    public void runLottoBatch() {
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
