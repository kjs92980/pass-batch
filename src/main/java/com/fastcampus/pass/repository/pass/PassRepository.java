package com.fastcampus.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {
    List<PassEntity> findByStatusAndEndedAtLessThanAndDeletedIsFalse(PassStatus status, LocalDateTime endedAt);

    @Transactional
    @Modifying
    @Query(value = "UPDATE PassEntity p " +
            "          SET p.status = :status, " +
            "              p.expiredAt = :expiredAt " +
            "        WHERE p.passSeq IN (:passSeqs)")
    int updateStatusAndExpiredAt(List<Integer> passSeqs, PassStatus status, LocalDateTime expiredAt);
}
