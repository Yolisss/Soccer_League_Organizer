package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class TeamList{
    private BufferedReader mReader;
    private Map<String, String> mTeams;

    public TeamList(){
        //converts from byte to char
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mTeams = new HashMap<>();
    }

    private String promptAction() throws IOException{
        System.out.println("What do you want to do: ");
        System.out.println("add - Create a new team ");
        System.out.println("list - Show all teams ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    //menu option to create a new team
    public void run(){
        String choice = "";
        do{
            try{
                choice = promptAction();
                switch(choice){
                    case "add":
                        Team newTeam = promptNewTeam();
                        addTeam(newTeam);
                        break;
                    case "list":
                        listTeams();
                        break;
                    default:
                        System.out.printf("Unknown choice: %s. Try again. %n%n%n", choice);
                }
            } catch(IOException ioe){
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while(!choice.equals("quit"));
    }

    //prompt to store team and coach name
    private Team promptNewTeam() throws IOException {
        System.out.println("Enter the team's name: ");
        String teamName = mReader.readLine();
        System.out.println("Enter the coach's name: ");
        String coachName = mReader.readLine();
        return new Team(teamName, coachName);
    }

    private void addTeam(Team team) {
        if (mTeams.containsKey(team.getTeamName())) {
            System.out.println("A team with this name already exists.");
        } else {
            mTeams.put(team.getTeamName(), team.getCoachName());
            System.out.printf("Added team: %s%n", team);
        }
    }

    private void listTeams() {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available.");
        } else {
            System.out.println("Current teams:");
            for (String team : mTeams.keySet()) {
                System.out.println(team);
            }
        }
    }
}






































//import java.util.ArrayList;
//import java.util.List;

//public class TeamList {
//    //ONE shared list of teams
//    private static List<Team> mTeams = new ArrayList<>();
//
//    //constructor
//    public TeamList() {
//        //initializing with emptyArr
//        mTeams = new ArrayList<Team>();
//    }
//
//    //data type team= holds team and coach var
//    //team is whatever input from the user
//    public static void addTeam(Team newTeam) {
//        //adding new team to list of mTeams
//        mTeams.add(newTeam);
//    }
//
//    //getter
//    public static List<Team> getTeams() {
//        return mTeams;
//    }
//}
