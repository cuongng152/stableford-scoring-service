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

    public CourseScore() {
    }

    public CourseScore(Integer stroke, String courseName, Timestamp dateOfPlay, Integer dailyHandicap) {
        this.stroke = stroke;
        this.courseName = courseName;
        this.dateOfPlay = dateOfPlay;
        this.dailyHandicap = dailyHandicap;
    }
}
