package kr.co.polycube.backendtest.global.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final LottoBatchProcess lottoBatchProcess;

    @Bean
    public Job lottoJob(JobRepository jobRepository, Step lottoStep) {
        return new JobBuilder("lottoCheck", jobRepository)
                .start(lottoStep)
                .build();
    }

    @Bean
    public Step lottoStep(JobRepository jobRepository, Tasklet lottoTask, PlatformTransactionManager transactionManager) {
        return new StepBuilder("lottoCheck", jobRepository)
                .tasklet(lottoTask, transactionManager)
                .build();
    }

    @Bean
    public Tasklet lottoTask() {
        return (contribution, chunkContext) -> {
            lottoBatchProcess.execute();
            return RepeatStatus.FINISHED;
        };
    }
}
