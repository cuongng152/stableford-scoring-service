package com.example.stablefordscoringservice.stablefordscore.controller;

import com.example.stablefordscoringservice.controller.StablefordScoringController;
import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.service.StablefordScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StablefordScoringController.class)
public class StablefordScoreControllerTest {

    @MockBean
    private StablefordScoringService stablefordScoringService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfStablefordScores() throws Exception {
        UUID id = UUID.randomUUID();
        List<StablefordScore> listOfScoresReturned = new ArrayList<>(
                Arrays.asList(
                        new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2),
                        new StablefordScore(id, "271220221", "140", "3", "9", "4", "1", 200.00, "Left", 2)
                )
        );

        when(stablefordScoringService.getAllScores()).thenReturn(listOfScoresReturned);
        mockMvc.perform(get("/api/v1/stableford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(listOfScoresReturned.size()))
                .andDo(print());
    }

    @Test
    void shouldAddNewStablefordScore() throws Exception {
        UUID id = UUID.randomUUID();
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        mockMvc.perform(post("/api/v1/stableford").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScore)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnStablefordScore() throws Exception {
        UUID id = UUID.randomUUID();
        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        Optional<StablefordScore> result = Optional.of(score);


        when(stablefordScoringService.getScoreById(id.toString())).thenReturn(result);
        mockMvc.perform(get("/api/v1/stableford/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.code").value(score.getCode()))
                .andExpect(jsonPath("$.length").value(score.getLength()))
                .andExpect(jsonPath("$.teeOffLength").value(score.getTeeOffLength()))
                .andDo(print());
    }

    @Test
    void shouldUpdateScore() throws Exception {
        UUID id = UUID.randomUUID();

        StablefordScore existingScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 210.00, "Miss Hit", 3);

        when(stablefordScoringService.getScoreById(id.toString())).thenReturn(Optional.of(existingScore));
        when(stablefordScoringService.updateScoreById(id.toString(), newScore)).thenReturn(newScore);

        mockMvc.perform(put("/api/v1/stableford/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teeOffLength").value(newScore.getTeeOffLength()))
                .andExpect(jsonPath("$.teeOffDirection").value(newScore.getTeeOffDirection()))
                .andDo(print());
    }
}
