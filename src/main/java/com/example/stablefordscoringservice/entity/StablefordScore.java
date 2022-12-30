package com.example.stablefordscoringservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
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
    private Double teeOffLength;
    private String teeOffDirection;
    private Integer putt;



    public StablefordScore() {
    }

    public StablefordScore(UUID id, String code, String length, String par, String index, String stroke, String stablefordScore, Double teeOffLength, String teeOffDirection, Integer putt) {
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
