package com.example.stablefordscoringservice.controller;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.service.coursescore.CourseScoringService;
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
@Tag(name = "Course Scoring Service Endpoints")
@RequestMapping(path = "/api/v1/course")
@CrossOrigin(origins = {"https://master.dg1gxs71ljkjp.amplifyapp.com", "https://127.0.0.1:3000"})
@ResponseStatus(HttpStatus.OK)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User not authorized"),
        @ApiResponse(responseCode = "404", description = "Not found")
})
public class CourseScoreController {
    @Autowired
    private CourseScoringService courseScoringService;

    @GetMapping
    /** Add security here*/
    public @ResponseBody List<CourseScore> getCourseScore() {
        List<CourseScore> response;
        response = courseScoringService.getAllScores();
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Score added"),
    })
    public @ResponseBody String create(@RequestBody CourseScore newScore) {
        return courseScoringService.addScore(newScore);
    }

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public @ResponseBody Optional<CourseScore> getById(@PathVariable String id) {
        return courseScoringService.getScoreById(id);
    }

    @GetMapping("/holecode/{holeCode}")
    /** Add security here*/
    public @ResponseBody List<CourseScore> getAllCourseScoresByHoleCode(@PathVariable String holeCode) {
        List<CourseScore> response;
        response = courseScoringService.getAllCourseScoresByHoleCode(holeCode);
        return response;
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Score updated"),
    })
    public @ResponseBody CourseScore updateById(@PathVariable String id, @RequestBody CourseScore updatedScore) {
        return courseScoringService.updateScoreById(id, updatedScore);
    }
}
