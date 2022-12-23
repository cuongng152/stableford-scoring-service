package com.example.stablefordscoringservice.entiry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "stableford_score")
@Data
public class StablefordScore {

    @Id
    @GeneratedValue(generator = "UUID")
//    @Type(type = "uuid-char")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private BigDecimal meter;
    private String par;
    private String index;
    private String stroke;
    private String stablefordScore;

}
