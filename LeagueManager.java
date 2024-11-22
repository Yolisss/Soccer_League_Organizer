import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.TeamList;

import java.util.Scanner;

public class LeagueManager {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Load players from the Players class (assuming this is already correctly implemented)
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);

    // Initialize the TeamList which will manage teams
    TeamList teamList = new TeamList();

    // Start the team management prompt in TeamList
      teamList.run();
  }
}
