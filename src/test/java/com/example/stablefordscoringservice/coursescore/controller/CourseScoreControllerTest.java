package com.example.stablefordscoringservice.coursescore.controller;

import com.example.stablefordscoringservice.controller.CourseScoreController;
import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.service.coursescore.CourseScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseScoreController.class)
public class CourseScoreControllerTest {

    @MockBean
    private CourseScoringService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID id = UUID.randomUUID();
    private String s = "2019-10-11T12:12:23.234Z";
    private Timestamp ts = Timestamp.from(Instant.parse(s));
    private CourseScore courseScore = new CourseScore(90, "Waterford", ts, 20, 189.6, 37);

    private CourseScore newScore = new CourseScore(93, "Waterford", ts, 19, 189.6, 37);

    @Test
    void shouldReturnListOfCourseScores() throws Exception {
        when(service.getAllScores()).thenReturn(Arrays.asList(courseScore));
        mockMvc.perform(get("/api/v1/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(Arrays.asList(courseScore).size()))
                .andDo(print());
    }

    @Test
    void shouldAddNewCourseScore() throws Exception {
        mockMvc.perform(post("/api/v1/course").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScore)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnCourseScore() throws Exception {
        when(service.getScoreById(id.toString())).thenReturn(Optional.of(courseScore));
        mockMvc.perform(get("/api/v1/course/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value(courseScore.getCourseName()))
                .andExpect(jsonPath("$.stroke").value(courseScore.getStroke()))
                .andExpect(jsonPath("$.dailyHandicap").value(courseScore.getDailyHandicap()))
                .andDo(print());
    }

    @Test
    void shouldUpdateScore() throws Exception {
        when(service.getScoreById(id.toString())).thenReturn(Optional.of(courseScore));
        when(service.updateScoreById(id.toString(), newScore)).thenReturn(newScore);

        mockMvc.perform(put("/api/v1/course/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stroke").value(newScore.getStroke()))
                .andExpect(jsonPath("$.dailyHandicap").value(newScore.getDailyHandicap()))
                .andDo(print());
    }
}
