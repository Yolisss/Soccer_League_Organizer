package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamList {
    private List<Team> mTeams;
    private BufferedReader mReader;

    public TeamList(BufferedReader reader) {
        mTeams = new ArrayList<>();
        mReader = new BufferedReader(new InputStreamReader(System.in));
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
            System.out.println("[6] League Balance Report");
            System.out.println("[7] Exit");

            try {
                choice = mReader.readLine();
                switch (choice) {
                    case "1":
                        addTeam();  // Calls the addTeam method
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
        List<Player> players = selectedTeam.getPlayers(); // Assuming this method exists in Team class
        if (players == null || players.isEmpty()) {
            System.out.println("No players to remove from the selected team.");
            return;
        }

        // Display players
        System.out.println("Players List:");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, players.get(i));
        }

        // Prompt for player selection
        System.out.println("Choose a player to remove by number:");
        int playerIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (playerIndex < 0 || playerIndex >= players.size()) {
            System.out.println("Invalid player choice.");
            return;
        }

        Player selectedPlayer = players.get(playerIndex);
        if (selectedTeam.removePlayer(selectedPlayer)) {  // Assuming removePlayer method exists in Team class
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
        mTeams.add(newTeam);  // Add the new team to the list

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

        // Get available players (Assuming the list is in the form of an array or a similar collection)
        Player[] players = Players.load(); // Assuming this method exists and returns an array of available players
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
        if (selectedTeam.addPlayer(selectedPlayer)) {  // Assuming addPlayer method exists in Team class
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
}
