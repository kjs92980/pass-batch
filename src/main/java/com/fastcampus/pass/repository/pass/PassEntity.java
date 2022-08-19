package com.fastcampus.pass.repository.pass;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "pass")
public class PassEntity {
    @Id
    private int passSeq;

    private String gymId;
    private String passId;
    private String userId;
    @Enumerated(EnumType.STRING)
    private PassStatus status;
    private int num;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime expiredAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private boolean deleted;
}