package com.fastcampus.pass.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

/*
 * @EnableBatchProcessing
 * Spring Batch 기능을 활성화하고 배치 작업을 설정하기 위한 기본 구성을 제공합니다.
 * JobRepository, JobLauncher, JobRegistry, PlatformTransactionManager, JobBuilderFactory, StepBuilderFactory 빈으로 제공됩니다.
 * https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/configuration/annotation/EnableBatchProcessing.html
 */

@EnableBatchProcessing
@Configuration
public class BatchConfig {
}
