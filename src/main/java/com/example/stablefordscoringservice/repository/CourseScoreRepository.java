package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CourseScoreRepository extends CrudRepository<CourseScore, String> {
}
