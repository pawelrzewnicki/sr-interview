package com.rzewnicki.sportradar.interview;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private List<Match> matches;

    public ScoreBoard(){
        this.matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam){
        this.matches.add(new Match(homeTeam, awayTeam));
    }
}
