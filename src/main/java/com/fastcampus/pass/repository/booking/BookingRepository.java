package com.fastcampus.pass.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE BookingEntity b" +
            "          SET b.usedPass = :usedPass," +
            "              b.modifiedAt = CURRENT_TIMESTAMP" +
            "        WHERE b.passSeq = :passSeq")
    int updateUsedPass(Integer passSeq, boolean usedPass);
}
