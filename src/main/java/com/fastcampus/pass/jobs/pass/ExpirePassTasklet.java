package com.fastcampus.pass.jobs.pass;

import com.fastcampus.pass.service.pass.PassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ExpirePassTasklet implements Tasklet {
    private final PassService passService;

    public ExpirePassTasklet(PassService passService) {
        this.passService = passService;
    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 대상 날짜 정하기
        final LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        log.info("ExpirePassTasklet: 이용권 만료 시작, endedAt={}", now);

        // 만료 대상 이용권 데이터 가져오기
        // 이용권 만료 상태로 업데이트
        int expiredCount = passService.expirePasses(now);
        log.info("ExpirePassTasklet: 이용권 만료 {}건 완료, endedAt={}", expiredCount, now);

        return RepeatStatus.FINISHED;
    }
}
