package com.rzewnicki.sportradar.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void testStartMatch() {
        scoreBoard.startMatch("Team 1", "Team 2");
        List<Match> matches = scoreBoard.getSummary();
        assertEquals(1, matches.size());
    }

    @Test
    public void testUpdateScore() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.updateScore("Team A", "Team B", 3, 2);
        List<Match> matches = scoreBoard.getSummary();
        assertEquals(3, matches.get(0).getHomeScore());
        assertEquals(2, matches.get(0).getAwayScore());
    }

    @Test
    public void testFinishMatch() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.finishMatch("Team A", "Team B");
        List<Match> matches = scoreBoard.getSummary();
        assertTrue(matches.isEmpty());
    }
}