package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.StablefordScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StablefordScoringRepository extends JpaRepository<StablefordScore, String> {
}
