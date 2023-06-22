package com.rzewnicki.sportradar.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void testStartMatch() {
        scoreBoard.startMatch("Team A", "Team B");
        List<Match> matches = scoreBoard.getSummary();
        assertEquals(1, matches.size());
    }

    @Test
    public void testStartMatchForAlreadyStartedMatch() {
        scoreBoard.startMatch("Team A", "Team B");
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.startMatch("Team A", "Team B");
        });
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

    @Test
    public void testGetSummary() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.startMatch("Team C", "Team D");
        scoreBoard.updateScore("Team A", "Team B", 2, 2);
        scoreBoard.updateScore("Team C", "Team D", 3, 2);

        List<Match> matches = scoreBoard.getSummary();
        assertEquals(2, matches.size());
        assertEquals("Team C", matches.get(0).getHomeTeam());
        assertEquals("Team A", matches.get(1).getHomeTeam());
    }

    @Test
    public void testUpdateScoreForNonExistentMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.updateScore("Team A", "Team B", 2, 1);
        });
    }

    @Test
    public void testFinishNonExistentMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.finishMatch("Team A", "Team B");
        });
    }
}