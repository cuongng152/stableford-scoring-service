package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.entity.StablefordScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScoreRepository extends CrudRepository<CourseScore, String> {
    Iterable<CourseScore> findAllByOrderByDateOfPlayDesc();

    @Query(
            value ="SELECT * FROM course_score WHERE hole_code = ?1",
            nativeQuery = true
    )
    List<CourseScore> findCourseScoresByHoleCode(String holeCode);
}
