package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class TeamList {
    private List<Team> mTeams;
    private BufferedReader mReader;

    public TeamList(BufferedReader reader) {
        mTeams = new ArrayList<>();
        mReader = reader;
    }

    public void run() {
        String choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("[1] Create a new team");
            System.out.println("[2] Add a player to a team");
            System.out.println("[3] View all teams");
            System.out.println("[4] Remove a player from a team");
            System.out.println("[5] View team height report");
            System.out.println("[6] View league balance report");
            System.out.println("[7] Print a team roster");
            System.out.println("[8] Exit");

            try {
                choice = mReader.readLine();
                switch (choice) {
                    case "1":
                        addTeam();
                        break;
                    case "2":
                        addPlayerToTeam();
                        break;
                    case "3":
                        viewTeams();
                        break;
                    case "4":
                        removePlayerFromTeam();
                        break;
                    case "5":
                        viewHeightReport();
                        break;
                    case "6":
                        viewLeagueBalanceReport();
                        break;
                    case "7":
                        printTeamRoster();
                        break;
                    case "8":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace();
            }
        } while (true);
    }

    private void removePlayerFromTeam() throws IOException {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available. Create a team first.");
            return;
        }

        System.out.println("Choose a team by number:");
        for (int i = 0; i < mTeams.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, mTeams.get(i).getTeamName());
        }

        int teamIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (teamIndex < 0 || teamIndex >= mTeams.size()) {
            System.out.println("Invalid team choice.");
            return;
        }

        Team selectedTeam = mTeams.get(teamIndex);

        // Get the players from the selected team
        Set<Player> players = selectedTeam.getPlayers();
        if (players == null || players.isEmpty()) {
            System.out.println("No players to remove from the selected team.");
            return;
        }

        // Display players
        System.out.println("Players List:");
        int i = 1;
        for (Player player : players) {
            System.out.printf("[%d] %s%n", i++, player);
        }

        // Prompt for player selection
        System.out.println("Choose a player to remove by number:");
        int playerIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (playerIndex < 0 || playerIndex >= players.size()) {
            System.out.println("Invalid player choice.");
            return;
        }

        Player selectedPlayer = new ArrayList<>(players).get(playerIndex);
        if (selectedTeam.removePlayer(selectedPlayer)) {
            System.out.println("Player removed from the team.");
        } else {
            System.out.println("Failed to remove player from the team.");
        }
    }

    // Add a new team
    private void addTeam() throws IOException {
        System.out.println("Enter team name:");
        String teamName = mReader.readLine();
        System.out.println("Enter coach name:");
        String coachName = mReader.readLine();

        Team newTeam = new Team(teamName, coachName);
        mTeams.add(newTeam);

        System.out.println("Team created successfully: " + newTeam);
    }

    private void addPlayerToTeam() throws IOException {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available. Create a team first.");
            return;
        }

        System.out.println("Choose a team by number:");
        for (int i = 0; i < mTeams.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, mTeams.get(i).getTeamName());
        }

        int teamIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (teamIndex < 0 || teamIndex >= mTeams.size()) {
            System.out.println("Invalid team choice.");
            return;
        }

        Team selectedTeam = mTeams.get(teamIndex);

        // Get available players
        Player[] players = Players.load();
        if (players == null || players.length == 0) {
            System.out.println("No players available to add.");
            return;
        }

        // Display players
        System.out.println("Available Players List:");
        for (int i = 0; i < players.length; i++) {
            System.out.printf("[%d] %s%n", i + 1, players[i]);
        }

        // Prompt for player selection
        System.out.println("Choose a player to add by number:");
        int playerIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (playerIndex < 0 || playerIndex >= players.length) {
            System.out.println("Invalid player choice.");
            return;
        }

        Player selectedPlayer = players[playerIndex];
        if (selectedTeam.addPlayer(selectedPlayer)) {
            System.out.println("Player added to the team.");
        } else {
            System.out.println("Failed to add player to the team.");
        }
    }

    // View all teams and their players
    private void viewTeams() {
        if (mTeams.isEmpty()) {
            System.out.println("No teams created yet.");
            return;
        }

        // Sort the list using the natural ordering
        Collections.sort(mTeams);

        for (Team team : mTeams) {
            System.out.println(team);
            for (Player player : team.getPlayers()) {
                System.out.println("  " + player);
            }
        }
    }

    private void viewHeightReport() throws IOException {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available. Create a team first.");
            return;
        }

        // Prompt user to select a team
        System.out.println("Choose a team by number:");
        for (int i = 0; i < mTeams.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, mTeams.get(i).getTeamName());
        }

        int teamIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (teamIndex < 0 || teamIndex >= mTeams.size()) {
            System.out.println("Invalid team choice.");
            return;
        }

        Team selectedTeam = mTeams.get(teamIndex);

        // Generate and display the height report
        Map<Integer, List<Player>> heightReport = selectedTeam.getHeightReport();
        System.out.println("\nList of players by height:");

        for (Map.Entry<Integer, List<Player>> entry : heightReport.entrySet()) {
            int height = entry.getKey();
            List<Player> players = entry.getValue();
            for (Player player : players) {
                System.out.printf("%s %s (%d inches - %s)%n",
                        player.getFirstName(),
                        player.getLastName(),
                        height,
                        player.isPreviousExperience() ? "experienced" : "inexperienced");
            }
        }
    }

    private void viewLeagueBalanceReport() {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available. Create teams first.");
            return;
        }

        System.out.println("\nLeague Balance Report:");
        Map<String, Map<String, Integer>> leagueReport = new HashMap<>();

        for (Team team : mTeams) {
            Map<String, Integer> playerCounts = team.getPlayerExperienceReport();
            leagueReport.put(team.getTeamName(), playerCounts);
        }

        // Display the league report
        for (Map.Entry<String, Map<String, Integer>> entry : leagueReport.entrySet()) {
            String teamName = entry.getKey();
            Map<String, Integer> playerCounts = entry.getValue();
            System.out.printf("%s: %d Experienced, %d Inexperienced%n",
                    teamName,
                    playerCounts.get("Experienced"),
                    playerCounts.get("Inexperienced"));
        }
    }

    private void printTeamRoster() {
        if (mTeams.isEmpty()) {
            System.out.println("No teams available. Create teams first.");
            return;
        }

        // Prompt user to select a team
        Team selectedTeam = selectTeam();
        if (selectedTeam == null) {
            return;
        }

        System.out.printf("Roster for Team: %s%n", selectedTeam.getTeamName());
        Set<Player> players = selectedTeam.getPlayers();

        if (players.isEmpty()) {
            System.out.println("No players in this team yet.");
            return;
        }

        // Display each player's stats
        for (Player player : players) {
            System.out.printf("%s %s(%d inches - %s)%n",
                    player.getFirstName(),
                    player.getLastName(),
                    player.getHeightInInches(),
                    player.isPreviousExperience() ? "Experienced" : "Inexperienced"
            );
        }
    }

    private Team selectTeam() {
        System.out.println("Select a team:");
        for (int i = 0; i < mTeams.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, mTeams.get(i).getTeamName());
        }

        try {
            String input = mReader.readLine();
            int choice = Integer.parseInt(input) - 1;

            if (choice < 0 || choice >= mTeams.size()) {
                System.out.println("Invalid choice. Please try again.");
                return null;
            }
            return mTeams.get(choice);
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred. Please try again.");
            return null;
        }
    }
}