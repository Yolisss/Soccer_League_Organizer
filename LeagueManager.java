import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.TeamList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LeagueManager {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      // Load players from the Players class
      Player[] players = Players.load();
      System.out.printf("There are currently %d registered players.%n", players.length);

      // Initialize the TeamList which will manage teams
      TeamList teamList = new TeamList(reader);

      teamList.run();

  }
}
