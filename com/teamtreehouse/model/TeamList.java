package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private List<Team> mTeams;
    private BufferedReader mReader;

    public TeamList() {
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
            System.out.println("[4] View all teams");
            System.out.println("[5] Exit");

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

    public void displayTeamsAlphabetically(List<Team> teams) {
        // Basic Bubble Sort to sort teams alphabetically
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = 0; j < teams.size() - i - 1; j++) {
                // Compare team names in alphabetical order
                if (teams.get(j).getTeamName().compareTo(teams.get(j + 1).getTeamName()) > 0) {
                    // Swap teams if out of order
                    Team temp = teams.get(j);
                    teams.set(j, teams.get(j + 1));
                    teams.set(j + 1, temp);
                }
            }
        }

        System.out.println("Available Teams (Alphabetically Sorted):");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
        }
    }


    public void removePlayerFromTeam() {
        // Display teams alphabetically
        displayTeamsAlphabetically(mTeams);

        try {
            // Prompt the user to choose a team
            System.out.print("Enter the number of the team to remove a player from: ");
            int teamIndex = Integer.parseInt(mReader.readLine()) - 1;

            // Validate team index
            if (teamIndex < 0 || teamIndex >= mTeams.size()) {
                System.out.println("Invalid team number. Returning to menu.");
                return;  // Exit back to the main menu without crashing
            }

            Team selectedTeam = mTeams.get(teamIndex);

            // Check if the team has players
            if (selectedTeam.getPlayers().isEmpty()) {
                System.out.println("This team has no players to remove.");
                return;  // Exit back to the main menu
            }

            // Show players on the selected team
            System.out.println("Players on the team: ");
            for (int i = 0; i < selectedTeam.getPlayers().size(); i++) {
                System.out.println((i + 1) + ". " + selectedTeam.getPlayers().get(i));
            }

            // Prompt user to choose a player to remove
            System.out.print("Enter the number of the player to remove: ");
            int playerIndex = Integer.parseInt(mReader.readLine()) - 1;

            // Validate player index
            if (playerIndex < 0 || playerIndex >= selectedTeam.getPlayers().size()) {
                System.out.println("Invalid player number. Returning to menu.");
                return;  // Exit back to the main menu
            }

            // Remove the selected player from the team
            selectedTeam.getPlayers().remove(playerIndex);
            System.out.println("Player removed from the team.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
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

    // Add a player to an existing team
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

        Player[] players = Players.load(); // Get the available players
        System.out.println("Choose a player by number:");
        for (int i = 0; i < players.length; i++) {
            System.out.printf("[%d] %s%n", i + 1, players[i]);
        }

        int playerIndex = Integer.parseInt(mReader.readLine()) - 1;
        if (playerIndex < 0 || playerIndex >= players.length) {
            System.out.println("Invalid player choice.");
            return;
        }

        Player selectedPlayer = players[playerIndex];
        if (selectedTeam.addPlayer(selectedPlayer)) {
            System.out.println("Player added to the team.");
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
}
