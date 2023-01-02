package com.example.stablefordscoringservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

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
    private String code;
    private String length;
    private String par;
    private String index;
    private String stroke;
    private String stablefordScore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hole_analysis_id", referencedColumnName = "id")
    private HoleAnalysis holeAnalysis;

    public StablefordScore() {
    }

    public StablefordScore(UUID id, String code, String length, String par, String index, String stroke, String stablefordScore) {
        super();
        this.id = id;
        this.code = code;
        this.length = length;
        this.par = par;
        this.index = index;
        this.stroke = stroke;
        this.stablefordScore = stablefordScore;
    }
}
