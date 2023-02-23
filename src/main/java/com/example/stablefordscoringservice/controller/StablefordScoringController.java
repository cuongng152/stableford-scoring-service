package com.example.stablefordscoringservice.controller;

import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.service.stablefordscore.StablefordScoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Tag(name = "Stableford Scoring Service Endpoints")
@RequestMapping(path = "/api/v1/stableford")
@CrossOrigin(origins = {"https://master.dg1gxs71ljkjp.amplifyapp.com", "https://127.0.0.1:3000", "https://localhost:3000"})
@ResponseStatus(HttpStatus.OK)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User not authorized"),
        @ApiResponse(responseCode = "404", description = "Not found")
})
public class StablefordScoringController {

    @Autowired
    private StablefordScoringService stablefordScoringService;

    @GetMapping
    /** Add security here*/
    public @ResponseBody List<StablefordScore> getStablefordScores() {
        List<StablefordScore> response;
        response = stablefordScoringService.getAllScores();
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Score added"),
    })
    public @ResponseBody String create(@RequestBody StablefordScore newScore) {
        return stablefordScoringService.addScore(newScore);
    }

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})

    public @ResponseBody Optional<StablefordScore> getById(@PathVariable String id) {
        return stablefordScoringService.getScoreById(id);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Score updated"),
    })
    public @ResponseBody StablefordScore updateById(@PathVariable String id, @RequestBody StablefordScore updatedScore) {
        return stablefordScoringService.updateScoreById(id, updatedScore);
    }

    @GetMapping("hole/{holeCode}")
    /** Add security here*/
    public @ResponseBody List<StablefordScore> getAllScoresByHoleCode(@PathVariable String holeCode) {
        List<StablefordScore> response;
        response = stablefordScoringService.getAllScoresByHoleCode(holeCode);
        return response;
    }
}
