package com.example.stablefordscoringservice.controller;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.service.StablefordScoringService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Tag(name = "Stableford Scoring Service Endpoints")
@RequestMapping(path = "/api/v1/stableford")
@CrossOrigin(origins = "*")
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
    public @ResponseBody StablefordScore getStablefordScores() {
//        return (StablefordScore) stablefordScoringService.getAllScores();
        return new StablefordScore();
    }
}
