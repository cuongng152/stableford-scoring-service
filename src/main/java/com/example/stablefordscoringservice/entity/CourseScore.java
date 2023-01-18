package com.example.stablefordscoringservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "course_score")
@Data
public class CourseScore {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "stroke", nullable = false)
    private Integer stroke;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "date_of_play", nullable = false)
    private Timestamp dateOfPlay;

    @Column(name = "daily_handicap", nullable = false)
    private Integer dailyHandicap;

    @Column(name = "average_driver_distance", nullable = false)
    private Double avgDriverDistance;

    @Column(name = "stableford_score", nullable = false)
    private Integer stablefordScore;

    public CourseScore() {
    }

    public CourseScore(Integer stroke, String courseName, Timestamp dateOfPlay, Integer dailyHandicap, Double avgDriverDistance, Integer stablefordScore) {
        this.stroke = stroke;
        this.courseName = courseName;
        this.dateOfPlay = dateOfPlay;
        this.dailyHandicap = dailyHandicap;
        this.avgDriverDistance = avgDriverDistance;
        this.stablefordScore = stablefordScore;
    }
}
