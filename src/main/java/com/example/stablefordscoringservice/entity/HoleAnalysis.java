package com.example.stablefordscoringservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "hole_analysis")
@Data
@Transactional
@NoArgsConstructor
public class HoleAnalysis {
    @Id
    private String id;
    private Double teeOffLength;
    private String teeOffDirection;
    private Integer putt;

    public HoleAnalysis(Double teeOffLength, String teeOffDirection, Integer putt) {
        this.teeOffLength = teeOffLength;
        this.teeOffDirection = teeOffDirection;
        this.putt = putt;
    }
}
