package com.example.stablefordscoringservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stableford_score")
@Data
public class StablefordScore {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String holeCode;
    private String length;
    private String par;
    private String holeIndex;
    private String stroke;
    private String score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hole_analysis_id", referencedColumnName = "id")
    private HoleAnalysis holeAnalysis;

    public StablefordScore() {
    }

    public StablefordScore(UUID id, String holeCode, String length, String par, String holeIndex, String stroke, String score) {
        this.id = id;
        this.holeCode = holeCode;
        this.length = length;
        this.par = par;
        this.holeIndex = holeIndex;
        this.stroke = stroke;
        this.score = score;
    }
}
