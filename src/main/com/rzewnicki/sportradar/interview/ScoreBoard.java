package com.rzewnicki.sportradar.interview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScoreBoard {
    private List<Match> matches;

    public ScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam, LocalDateTime startTime) {
        if(findMatch(homeTeam, awayTeam).isPresent()){
            throw new IllegalArgumentException("Match is already started!");
        }

        if(homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Team names must not be null or empty!");
        }

        this.matches.add(new Match(homeTeam, awayTeam, startTime));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if(homeScore < 0 || awayScore < 0){
            throw new IllegalArgumentException("Score must be positive!");
        }

        findMatch(homeTeam, awayTeam)
                .orElseThrow(() -> new IllegalArgumentException("Match not found!"))
                .setScore(homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam)
                .orElseThrow(() -> new IllegalArgumentException("Match not found!"));

        matches.remove(match);
    }

    public List<Match> getSummary() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).thenComparing(Match::getStartTime).reversed())
                .collect(Collectors.toList());
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                .findFirst();
    }
}
