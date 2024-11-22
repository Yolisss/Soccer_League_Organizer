package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.Collections;
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

    // Method to display players sorted alphabetically by last name and then first name
    public void displayPlayersAlphabetically() {
        if (mPlayers.isEmpty()) {
            System.out.println("No players on this team.");
            return;
        }

        // Sort players alphabetically using the compareTo method defined in Player class
        Collections.sort(mPlayers);

        System.out.println("Players on " + mTeamName + ":");
        for (Player player : mPlayers) {
            System.out.println(player); // Will call Player's toString method
        }
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

        // Add the player and then sort the list to maintain alphabetical order
        mPlayers.add(player);
        Collections.sort(mPlayers);  // Ensures the list stays sorted
        return true;
    }

    public boolean removePlayer(Player player) {
        boolean removed = mPlayers.remove(player);

        if (removed) {
            Collections.sort(mPlayers);  // Sort again after removing a player
        }
        return removed;
    }

    @Override
    public String toString() {
        return String.format("Team: %s, Coach: %s", mTeamName, mCoachName);
    }
}
