package com.example.stablefordscoringservice.stablefordscore.controller;

import com.example.stablefordscoringservice.controller.StablefordScoringController;
import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.service.stablefordscore.StablefordScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StablefordScoringController.class)
public class StablefordScoreControllerTest {

    @MockBean
    private StablefordScoringService stablefordScoringService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static UUID id = UUID.randomUUID();

    private static StablefordScore score1 = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3");

    private static StablefordScore score2 = new StablefordScore(id, "271220221", "140", "3", "9", "4", "1");

    @Test
    void shouldReturnListOfStablefordScores() throws Exception {
        List<StablefordScore> listOfScoresReturned = new ArrayList<>(Arrays.asList(score1, score2));

        when(stablefordScoringService.getAllScores()).thenReturn(listOfScoresReturned);
        mockMvc.perform(get("/api/v1/stableford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(listOfScoresReturned.size()))
                .andDo(print());
    }

    @Test
    void shouldAddNewStablefordScore() throws Exception {
        mockMvc.perform(post("/api/v1/stableford").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(score1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnStablefordScore() throws Exception {
        Optional<StablefordScore> result = Optional.of(score1);
        when(stablefordScoringService.getScoreById(id.toString())).thenReturn(result);
        mockMvc.perform(get("/api/v1/stableford/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.holeCode").value(score1.getHoleCode()))
                .andExpect(jsonPath("$.length").value(score1.getLength()))
                .andExpect(jsonPath("$.par").value(score1.getPar()))
                .andDo(print());
    }

    @Test
    void shouldUpdateScore() throws Exception {
        when(stablefordScoringService.getScoreById(id.toString())).thenReturn(Optional.of(score1));
        when(stablefordScoringService.updateScoreById(id.toString(), score2)).thenReturn(score2);

        mockMvc.perform(put("/api/v1/stableford/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(score2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length").value(score2.getLength()))
                .andExpect(jsonPath("$.par").value(score2.getPar()))
                .andDo(print());
    }
}
