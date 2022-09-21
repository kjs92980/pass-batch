package com.fastcampus.pass.job.statistics;

import com.fastcampus.pass.repository.statistics.AggregatedStatistics;
import com.fastcampus.pass.repository.statistics.StatisticsRepository;
import com.fastcampus.pass.util.CustomCSVWriter;
import com.fastcampus.pass.util.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@StepScope
public class MakeWeeklyStatisticsTasklet implements Tasklet {
    @Value("#{jobParameters[from]}")
    private String fromString;
    @Value("#{jobParameters[to]}")
    private String toString;

    private final StatisticsRepository statisticsRepository;

    public MakeWeeklyStatisticsTasklet(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final LocalDateTime from = LocalDateTimeUtils.parse(fromString);
        final LocalDateTime to = LocalDateTimeUtils.parse(toString);

        final List<AggregatedStatistics> statisticsList = statisticsRepository.findByStatisticsAtBetweenAndGroupBy(from, to);
        Map<Integer, AggregatedStatistics> weeklyStatisticsEntityMap = new LinkedHashMap<>();

        for (AggregatedStatistics statistics : statisticsList) {
            int week = LocalDateTimeUtils.getWeekOfYear(statistics.getStatisticsAt());
            AggregatedStatistics savedStatisticsEntity = weeklyStatisticsEntityMap.get(week);

            if (savedStatisticsEntity == null) {
                weeklyStatisticsEntityMap.put(week, statistics);

            } else {
                savedStatisticsEntity.merge(statistics);

            }

        }

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"week", "allCount", "attendedCount", "cancelledCount"});
        weeklyStatisticsEntityMap.forEach((week, statistics) -> {
            data.add(new String[]{
                    "Week " + week,
                    String.valueOf(statistics.getAllCount()),
                    String.valueOf(statistics.getAttendedCount()),
                    String.valueOf(statistics.getCancelledCount())
            });

        });
        CustomCSVWriter.write("weekly_statistics_" + LocalDateTimeUtils.format(from, LocalDateTimeUtils.YYYY_MM_DD) + ".csv", data);
        return RepeatStatus.FINISHED;

    }
}
