package com.rzewnicki.sportradar.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreBoard {
    private List<Match> matches;

    public ScoreBoard(){
        this.matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam){
        this.matches.add(new Match(homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore){
        findMatch(homeTeam, awayTeam).ifPresent(m -> m.setScore(homeScore, awayScore));
    }
    public void finishMatch(String homeTeam, String awayTeam) {}

    public List<Match> getSummary(){
        return matches;
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam){
        return  matches.stream().filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam)).findFirst();
    }
}
