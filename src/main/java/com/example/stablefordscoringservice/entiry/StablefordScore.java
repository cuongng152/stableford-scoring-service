package com.example.stablefordscoringservice.entiry;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "stableford_score")
@Data
public class StablefordScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String length;
    private String par;
    private String index;
    private String stroke;
    private String stablefordScore;
    private Double teeOffLength;
    private String teeOffDirection;
    private Integer putt;



    public StablefordScore() {
    }

    public StablefordScore(Long id, String code, String length, String par, String index, String stroke, String stablefordScore, Double teeOffLength, String teeOffDirection, Integer putt) {
        this.id = id;
        this.code = code;
        this.length = length;
        this.par = par;
        this.index = index;
        this.stroke = stroke;
        this.stablefordScore = stablefordScore;
        this.teeOffLength = teeOffLength;
        this.teeOffDirection = teeOffDirection;
        this.putt = putt;
    }
}
