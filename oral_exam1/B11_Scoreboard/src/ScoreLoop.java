import java.util.Map;
import java.util.Scanner;
/**
 * This class has only a no argument constructor, 4 private methods, 3 private final variables and
 * 3 private variables.
 *
 * @author Max Finch
 */

public class ScoreLoop {
    /**
     * Scanner object used to read all user input
     */
    private final Scanner sc = new Scanner(System.in);
    /**
     * When the user chooses the game to score it is assigned to finalGameOption and the option CANNOT be changed.
     */
    private final int finalGameOption;
    /**
     * When the user chooses the game to score this variable stores the selection that was set in validInput() (called in mainMenuScreen).
     * Because the different printing components are separated for sake of organization, we cannot assign finalGameOption with
     * notFinalGameOption directly.
     */
    private int notFinalGameOption;
    /**
     * Holds the home team name. This variable is not final because like notFinalGameOption it is assigned outside the constructor.
     */
    private String homeTeam;
    /**
     * Holds the away team name. This variable is not final because like notFinalGameOption it is assigned outside the constructor.
     */
    private String awayTeam;
    /**
     * Keeps hold of the Game type that the user selects. This variable IS FINAL because there is no need to change the type of game
     * mid-match.
     */
    private final Game game;
    /**
     * No argument constructor. Launches the menu screen, selects and starts the game loop.
     */
    public ScoreLoop()
    {
        mainMenuScreen();
        //Two game option variables exist because finalGameOption MUST be initialized in the constructor.
        //notFinalGameOption cannot affect the program by re-assignment because its value gets immediately stored
        //in finalGameOption.
        finalGameOption = notFinalGameOption;
        game = selectGameLoop(finalGameOption);
        gameLoop();

    }
    /**
     * This method displays the main menu. It uses validInput() to verify each option selected (In this case: game type, home team, away team)
     */
    private void mainMenuScreen()
    {

        System.out.println("Select the game you would like to score");
        System.out.println("1. Soccer");
        System.out.println("2. Basketball");
        System.out.println("3. Baseball");
        System.out.println("4. Football");
        System.out.println("5. Hockey (International Rules)");
        validInput(1);

        System.out.println("Enter Home Team: ");
        validInput(2);
        homeTeam = sc.nextLine();
        System.out.println("Enter Away Team: ");
        validInput(2);
        awayTeam = sc.nextLine();

    }
    /**
     * validInput handles three types of input:
     * Input type 1: Game selection validation
     * Input type 2: Team name validation
     * Input type 3: In-Game choice validation
     * @param inputType type of selection being made
     */
    private void validInput(int inputType)
    {
        boolean valid = false;
        if(inputType == 1)
        {
            do {
                try {
                    System.out.println("Enter Choice: ");
                    if (sc.hasNextInt()) {
                        //We must parse the int from the whole line because the scanner cursor will stop at the int and not automatically go to a new line.
                        int tmp = Integer.parseInt(sc.nextLine());
                        if (tmp == 1 || tmp == 2 || tmp == 3 || tmp == 4 || tmp == 5) {
                            notFinalGameOption = tmp;
                            valid = true;

                        } else {
                            System.out.println("Invalid Input");
                            sc.nextLine();
                        }
                    } else {

                        System.out.println("Invalid Input");
                        sc.nextLine();
                    }
                }catch (Exception e)
                {
                    System.out.println("Invalid Input");
                    sc.nextLine();
                }
            }while(!valid);
        }else if(inputType == 2)
        {
            do {
                try {
                    if (!sc.hasNextInt()) {
                        valid = true;

                    } else {

                        System.out.println("Invalid Input. Team name must contain letters.");
                        sc.nextLine();
                    }
                }catch (Exception e)
                {
                    System.out.println("Invalid Input. Team name must contain letters.");
                    sc.nextLine();
                }
            }while(!valid);
        }else if(inputType == 3) {
            do {
                try {
                    System.out.println("Enter Choice: ");
                    int selection = (game.getScoringTypes().size() * 2) + 1;
                    if (sc.hasNextInt()) {
                        //We must parse the int from the whole line because the scanner cursor will stop at the int and not automatically go to a new line.
                        int tmp = Integer.parseInt(sc.nextLine());
                        if (tmp <= selection && tmp > 0) {
                            notFinalGameOption = tmp;
                            valid = true;

                        } else {
                            System.out.println("Invalid Input");

                        }
                    } else {

                        System.out.println("Invalid Input");
                        sc.nextLine();
                    }
                }catch(Exception e)
                {
                    System.out.println("Invalid Input");
                    sc.nextLine();
                }
            } while (!valid);
        }
    }
    /**
     * Selects the game to start. Assigns ScoreLoop's Game variable
     * to the selected gameOption. The different cases for gameOption
     * correspond to the print statements in mainMenuScreen()
     * @param gameOption index of the type of game to be scored
     * @return selected Game object
     */
    private Game selectGameLoop(int gameOption)
    {
        //Make new Game object
        Game tmpGame = null;

        switch (gameOption) {
            case 1 -> tmpGame = new SoccerGame(homeTeam, awayTeam);
            case 2 -> tmpGame = new BasketballGame(homeTeam, awayTeam);
            case 3 -> tmpGame = new BaseballGame(homeTeam, awayTeam);
            case 4 -> tmpGame = new FootballGame(homeTeam, awayTeam);
            case 5 -> tmpGame = new HockeyGame(homeTeam, awayTeam);
        }
        return tmpGame;

    }
    /**
     * This method displays ANY type of game. No code here is game specific.
     * Displays the current score of the game, current game "period", scoring options
     * for both teams and period ending for the game.
     * This method will take user input until the last "period" for the selected game type
     * reaches its end. It will then decide the winner and print a replay of every score
     * each team made in each "period"
     * If there is a tie the method will check if the selected game allows overtime. If the Game
     * goes into overtime it will run until the Game's specific rules say it should end.
     * (First to score, another inning, etc.). When it ends the replay will be
     * shown.
     *
     * If there is a tie and overtime is NOT allowed. The Game will end as a tie and the replay will be
     * shown.
     */
    private void gameLoop() {
        int selection;
        int exitOption = (game.getScoringTypes().size() * 2) + 1;
        while (!game.gameOverCheck()){
            selection = 1;
            System.out.println(homeTeam + " - " + game.getHomeScore() + ", " + awayTeam + " - " + game.getAwayScore());
            System.out.println("Current " + game.getPeriodOfPlay() + "\n");
            System.out.println("Menu:");

            for (Map.Entry<String, Integer> scoreType : game.getScoringTypes().entrySet()) {
                System.out.println(selection + ". " + homeTeam + " " + scoreType.getKey());
                selection++;
            }
            for (Map.Entry<String, Integer> scoreType : game.getScoringTypes().entrySet()) {
                System.out.println(selection + ". " + awayTeam + " " + scoreType.getKey());
                selection++;
            }

            System.out.println(exitOption + ". " + "End " + game.getPeriodOfPlay());
            validInput(3);
            game.selectPlay(notFinalGameOption);

            if(game.gameOverCheck() && game.determineWinner() == 3 && game.getOvertime()) //if we have a tie and the last period has ended. Continue the game.
            {
                game.setMaxPeriodNum(game.getMaxPeriodNum() + 1);
            }
        }
        System.out.println("\nGame is over");
        System.out.println(homeTeam + " - " + game.getHomeScore() + ", " + awayTeam + " - " + game.getAwayScore());
        System.out.println(game.getWinner());
        System.out.println("\nREPLAY:\n");
        for (int i=0; i<game.getGameLog().size(); i++)
        {
            System.out.println(game.getGameLog().get(i));
        }

    }

}
