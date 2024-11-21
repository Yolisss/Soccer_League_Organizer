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
    System.out.print("What would you like to do? " +
            "[Create a new team, Add players to a team, Remove players from a team]"
    );
    String userItemInput = scanner.nextLine();

    //team name info
    System.out.print("Give your team a name: ");
    String teamInfo = scanner.nextLine();

    //coach name info
    System.out.print("Coach name: ");
    String coachInfo = scanner.nextLine();

//    System.out.printf("Your team name is: %s%n%n" + "Your coach's name is: %s%n%n", teamInfo, coachInfo);
    Team newTeam = new Team(teamInfo, coachInfo);
    TeamList.addTeam(newTeam);

    for(Team team : TeamList.getTeams()){
      System.out.println(team);
    }
  }
}
