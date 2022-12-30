package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseScoreRepository extends CrudRepository<CourseScore, UUID> {
}
