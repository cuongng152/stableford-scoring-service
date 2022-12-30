package com.example.stablefordscoringservice.controller;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.service.StablefordScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        long id = 1L;
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
        long id = 1L;
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        mockMvc.perform(post("/api/v1/stableford").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScore)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
