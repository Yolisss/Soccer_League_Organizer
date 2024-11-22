package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String mTeamName;
    private String mCoachName;
    private List<Player> mPlayers;

    public Team(String teamName, String coachName) {
        mTeamName = teamName;
        mCoachName = coachName;
        mPlayers = new ArrayList<>();
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public boolean addPlayer(Player player) {
        // Check if the player is already on the team
        if (mPlayers.contains(player)) {
            System.out.println(player.getFirstName() + " " + player.getLastName() + " is already on the team.");
            return false;
        }

        if (mPlayers.size() >= 11) {
            System.out.println("Team is full. Cannot add more players.");
            return false;
        }

        mPlayers.add(player);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Team: %s, Coach: %s", mTeamName, mCoachName);
    }
}
