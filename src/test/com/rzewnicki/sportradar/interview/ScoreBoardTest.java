package com.rzewnicki.sportradar.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        List<Match> matches = scoreBoard.getSummary();
        assertEquals(1, matches.size());
    }

    @Test
    public void testStartMatchForAlreadyStartedMatch() {
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now()));
    }

    @Test
    public void testStartMatchForNullTeam() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startMatch(null, "Team B", LocalDateTime.now()));
    }

    @Test
    public void testUpdateScore() {
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        scoreBoard.updateScore("Team A", "Team B", 3, 2);
        List<Match> matches = scoreBoard.getSummary();
        assertEquals(3, matches.get(0).getHomeScore());
        assertEquals(2, matches.get(0).getAwayScore());
    }

    @Test
    public void testUpdateScoreForNegativeScore() {
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateScore("Team A", "Team B", -2, 1));
    }

    @Test
    public void testFinishMatch() {
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        scoreBoard.finishMatch("Team A", "Team B");
        List<Match> matches = scoreBoard.getSummary();
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetSummary() {
        scoreBoard.startMatch("Team A", "Team B", LocalDateTime.now());
        scoreBoard.startMatch("Team C", "Team D", LocalDateTime.now());
        scoreBoard.startMatch("Team E", "Team F", LocalDateTime.now().minusHours(1));
        scoreBoard.updateScore("Team A", "Team B", 2, 2);
        scoreBoard.updateScore("Team C", "Team D", 3, 2);
        scoreBoard.updateScore("Team E", "Team F", 3, 2);

        List<Match> matches = scoreBoard.getSummary();
        assertEquals(3, matches.size());
        assertEquals("Team C", matches.get(0).getHomeTeam());
        assertEquals("Team E", matches.get(1).getHomeTeam());
        assertEquals("Team A", matches.get(2).getHomeTeam());
    }

    @Test
    public void testUpdateScoreForNonExistentMatch() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateScore("Team A", "Team B", 2, 1));
    }

    @Test
    public void testFinishNonExistentMatch() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.finishMatch("Team A", "Team B"));
    }
}