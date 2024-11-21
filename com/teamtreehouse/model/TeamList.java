package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class TeamList {
    //ONE shared list of teams
    private static List<Team> mTeams = new ArrayList<>();

    //constructor
    public TeamList() {
        //initializing with emptyArr
        mTeams = new ArrayList<Team>();
    }

    //data type team= holds team and coach var
    //team is whatever input from the user
    public static void addTeam(Team newTeam) {
        //adding new team to list of mTeams
        mTeams.add(newTeam);
    }

    //getter
    public static List<Team> getTeams() {
        return mTeams;
    }
}
