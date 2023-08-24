package com.example.namo2.moim;

import com.example.namo2.entity.moimmemo.MoimMemo;
import com.example.namo2.entity.moimschedule.MoimSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MoimMemoRepository extends JpaRepository<MoimMemo, Long> {
    public boolean existsMoimMemoByMoimSchedule(MoimSchedule moimSchedule);

    @Query(value = "select distinct mm" +
            " from MoimMemo mm" +
            " join fetch mm.moimSchedule ms" +
            " join fetch ms.moimScheduleAndUsers msu" +
            " join fetch msu.user" +
            " where ms.id = :moimScheduleId")
    public MoimMemo findMoimMemoAndUsersByMoimSchedule(Long moimScheduleId);

    public Optional<MoimMemo> findMoimMemoByMoimSchedule(MoimSchedule moimSchedule);

}