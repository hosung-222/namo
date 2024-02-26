package com.example.namo2.domain.memo.dao.repository;

import com.example.namo2.domain.memo.domain.MoimMemo;
import com.example.namo2.domain.memo.domain.MoimMemoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MoimMemoLocationRepository extends JpaRepository<MoimMemoLocation, Long>, MoimMemoLocationRepositoryCustom {

    @Query(value = "select ml" +
            " from MoimMemoLocation ml" +
            " left join fetch ml.moimMemoLocationImgs" +
            " where ml.id = :locationId")
    Optional<MoimMemoLocation> findMoimMemoLocationById(@Param("locationId") Long locationId);

    @Query("select ml " +
            "from MoimMemoLocation ml" +
            " where ml.moimMemo = :moimMemo")
    Optional<List<MoimMemoLocation>> findMoimMemoLocationsByMoimMemo(MoimMemo moimMemo);

    @Query("select mml " +
            "from MoimMemoLocation mml " +
            "where mml.moimMemo = :moimMemo")
    List<MoimMemoLocation> findMoimMemo(@Param("moimMemo") MoimMemo moimMemo);
}
