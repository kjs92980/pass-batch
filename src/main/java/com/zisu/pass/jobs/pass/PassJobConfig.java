package com.zisu.pass.jobs.pass;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PassJobConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;
    private final ReleasePassTasklet releasePassTasklet;

    public PassJobConfig(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory, ReleasePassTasklet releasePassTasklet) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
        this.releasePassTasklet = releasePassTasklet;
    }

    @Bean
    public Step releasePassStep() {
        return stepBuilderFactory.get("releasePassStep")
                .tasklet(releasePassTasklet)
                .build();
    }

    @Bean
    public Job releasePassJob() {
        return jobBuilderFactory.get("releasePassJob")
                .incrementer(new RunIdIncrementer())
                .start(releasePassStep())
                .build();
    }

}
