package com.fastcampus.pass.repository.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AggregatedStatistics {
    private LocalDateTime statisticsAt; // 일 단위
    private long allCount;
    private long attendedCount;
    private long cancelledCount;

    public void merge(final AggregatedStatistics statistics) {
        this.allCount += statistics.getAllCount();
        this.attendedCount += statistics.getAttendedCount();
        this.cancelledCount += statistics.getCancelledCount();

    }
}
