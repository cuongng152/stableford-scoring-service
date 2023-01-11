package com.example.stablefordscoringservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hole_analysis")
@Data
public class HoleAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double teeOffLength;
    private String teeOffDirection;
    private Integer putt;

    @OneToOne
    @JoinColumn(name = "HoleAnalysis")
    private StablefordScore stablefordScore;

    public HoleAnalysis() {
    }

    public HoleAnalysis(Double teeOffLength, String teeOffDirection, Integer putt) {
        this.teeOffLength = teeOffLength;
        this.teeOffDirection = teeOffDirection;
        this.putt = putt;
    }
}
