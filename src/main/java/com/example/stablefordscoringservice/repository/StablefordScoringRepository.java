package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.StablefordScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StablefordScoringRepository extends JpaRepository<StablefordScore, String> {
    @Query(
            value ="SELECT * FROM stableford_score WHERE hole_code = ?1",
            nativeQuery = true
    )
    List<StablefordScore> findStablefordScoresByHoleCode(String holeCode);
}
