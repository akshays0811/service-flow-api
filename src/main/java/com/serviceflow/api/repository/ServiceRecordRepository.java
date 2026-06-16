package com.serviceflow.api.repository;

import com.serviceflow.api.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, UUID> {

    @Query("SELECT s FROM ServiceRecord s " +
            "JOIN FETCH s.customer c " +
            "WHERE s.nextServiceDate = :targetDate " +
            "AND s.reminderSent = false")
    List<ServiceRecord> findPendingReminders(@Param("targetDate") LocalDate targetDate);
}
