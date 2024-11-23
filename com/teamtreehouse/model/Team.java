package com.teamtreehouse.model;

import java.util.*;

public class Team implements Comparable<Team>{
    private String mTeamName;
    private String mCoachName;
    private Set<Player> mPlayers;

    public Team(String teamName, String coachName) {
        mTeamName = teamName;
        mCoachName = coachName;
        mPlayers = new TreeSet<>(); // TreeSet maintains sorted order
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public Set<Player> getPlayers() {
        return mPlayers;
    }

    public boolean addPlayer(Player player) {
        if (mPlayers.size() >= 11) {
            System.out.println("Team is full. Cannot add more players.");
            return false;
        }

        boolean added = mPlayers.add(player); // Automatically checks for duplicates
        if (!added) {
            System.out.println(player.getFirstName() + " " + player.getLastName() + " is already on the team.");
        }
        return added;
    }

    public boolean removePlayer(Player player) {
        return mPlayers.remove(player);
    }

    public Map<Integer, List<Player>> getHeightReport() {
        Map<Integer, List<Player>> heightGroups = new HashMap<>();

        for (Player player : mPlayers) {
            int height = player.getHeightInInches();
            heightGroups.putIfAbsent(height, new ArrayList<>());
            heightGroups.get(height).add(player);
        }

        return heightGroups;
    }

    public Map<String, Integer> getPlayerExperienceReport() {
        int experienced = 0;
        int inexperienced = 0;

        for (Player player : mPlayers) {
            if (player.isPreviousExperience()) {
                experienced++;
            } else {
                inexperienced++;
            }
        }

        Map<String, Integer> experienceReport = new HashMap<>();
        experienceReport.put("Experienced", experienced);
        experienceReport.put("Inexperienced", inexperienced);
        return experienceReport;
    }

    @Override
    public int compareTo(Team other) {
        return this.mTeamName.compareToIgnoreCase(other.mTeamName);
    }


    @Override
    public String toString() {
        return String.format("Team: %s, Coach: %s", mTeamName, mCoachName);
    }
}
