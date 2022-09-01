package com.fastcampus.pass.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

/*
 * @EnableBatchProcessing
 * Spring Batch 기능을 활성화하고 배치 작업을 설정하기 위한 기본 구성을 제공합니다.
 * JobBuilderFactory, StepBuilderFactory를 빈으로 등록되어 Job, Step 구현 시 사용할 수 있습니다.
 */

@EnableBatchProcessing
@Configuration
public class BatchConfig {
}
