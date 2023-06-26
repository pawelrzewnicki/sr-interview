package com.rzewnicki.sportradar.interview;

import java.time.LocalDateTime;

public class Match {

    private final String homeTeam;
    private final String awayTeam;

    private int homeScore;
    private int awayScore;

    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam,LocalDateTime startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = startTime;
    }

    void setScore(int homeScore, int awayScore){
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    int getTotalScore(){
        return homeScore + awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }


}
