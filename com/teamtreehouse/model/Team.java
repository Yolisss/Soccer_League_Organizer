package com.teamtreehouse.model;


public class Team {
    private String mTeamName;
    private String mCoachName;

    public Team(String teamName, String coachName) {
        mTeamName = teamName;
        mCoachName = coachName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    @Override
    public String toString(){
        return String.format("Team: %s, Coach: %s", getTeamName(), getCoachName());
    }
}
