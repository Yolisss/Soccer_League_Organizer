import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.TeamList;

import java.util.Scanner;

public class LeagueManager {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    //start of prompt
// Initialize the TeamList
    TeamList teamList = new TeamList();

    // Start the team management prompt
    teamList.run();
  }
}
