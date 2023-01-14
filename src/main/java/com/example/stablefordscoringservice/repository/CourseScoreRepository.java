package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseScoreRepository extends CrudRepository<CourseScore, String> {
}
