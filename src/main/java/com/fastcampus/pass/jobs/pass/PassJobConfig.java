package com.fastcampus.pass.jobs.pass;

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
    private final ExpirePassTasklet expirePassTasklet;

    public PassJobConfig(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory, ExpirePassTasklet expirePassTasklet) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
        this.expirePassTasklet = expirePassTasklet;
    }

    @Bean
    public Step expirePassStep() {
        return stepBuilderFactory.get("expirePassStep")
                .tasklet(expirePassTasklet)
                .build();
    }

    @Bean
    public Job expirePassJob() {
        return jobBuilderFactory.get("expirePassJob")
                .incrementer(new RunIdIncrementer())
                .start(expirePassStep())
                .build();
    }

}
