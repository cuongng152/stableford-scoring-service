package com.example.stablefordscoringservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "stableford_score")
@Data
@Transactional
@NoArgsConstructor
public class StablefordScore {

    @Id
    private String id;
    private String holeCode;
    private String length;
    private Integer par;
    private Integer holeIndex;
    private Integer stroke;
    private Integer score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hole_analysis_id", referencedColumnName = "id")
    private HoleAnalysis holeAnalysis;

    public StablefordScore(String holeCode, String length, Integer par, Integer holeIndex, Integer stroke, Integer score) {
        this.holeCode = holeCode;
        this.length = length;
        this.par = par;
        this.holeIndex = holeIndex;
        this.stroke = stroke;
        this.score = score;
    }
}
