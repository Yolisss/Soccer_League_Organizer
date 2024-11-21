package com.teamtreehouse.model;

import java.util.List;

public class Team {
    private String mTeamName;
    private String mCoachName;

    public Team(String coachName, String teamName) {
        mCoachName = coachName;
        mTeamName = teamName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }
}
