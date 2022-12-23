package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StablefordScoringRepository  extends CrudRepository<StablefordScore, UUID> {
}
