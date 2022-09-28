import java.util.Map;
import java.util.Scanner;

public class ScoreLoop {
    private final Scanner sc = new Scanner(System.in);
    private final int finalGameOption;
    private int notFinalGameOption;
    private String homeTeam;
    private String awayTeam;


    private final Game game;
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
    private void mainMenuScreen()
    {

        System.out.println("Select the game you would like to score");
        System.out.println("1. Soccer");
        System.out.println("2. Basketball");
        System.out.println("3. Baseball");
        System.out.println("4. Football");
        System.out.println("5. Hockey");
        validInput(1);

        System.out.println("Enter Home Team: ");
        validInput(2);
        homeTeam = sc.nextLine();
        System.out.println("Enter Away Team: ");
        validInput(2);
        awayTeam = sc.nextLine();

    }
    private void validInput(int inputType)
    {
        //Input type 1: Game selection validation
        //Input type 2: Team name validation
        //Input type 3: Game choice validation //Not yet implemented
        Boolean valid = false;
        if(inputType == 1)
        {
            do {
                System.out.println("Enter Choice: ");
                if (sc.hasNextInt()){
                    //We must parse the int from the whole line because the scanner cursor will stop at the int and not automatically go to a new line.
                    int tmp = Integer.parseInt(sc.nextLine());
                    if(tmp== 1 || tmp == 2 || tmp==3 || tmp==4 || tmp==5)
                    {
                        notFinalGameOption = tmp;
                        valid = true;

                    }else {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                    }
                }else{

                    System.out.println("Invalid Input");
                    sc.nextLine();
                }
            }while(!valid);
        }else if(inputType == 2)
        {
            do {
                if (!sc.hasNextInt()){
                    valid = true;

                }else{

                    System.out.println("Invalid Input. Team name must contain letters.");
                    sc.nextLine();
                }
            }while(!valid);
        }else if(inputType == 3) {
            do {
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

            } while (!valid);
        }
    }

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
    private void gameLoop() {
        int selection;
        int exitOption = (game.getScoringTypes().size() * 2) + 1;
        while (!game.gameOverCheck()){
            selection = 1;
            System.out.println(homeTeam + " - " + game.getHomeScore() + ", " + awayTeam + " - " + game.getAwayScore());
            System.out.println("Current " + game.getPeriodOfPlay() + "\n");
            //for loop to print menu
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
            validInput(3); //will set notFinalGameOption to the inputted value
            //choose action to do
            game.selectPlay(notFinalGameOption);
            if(game.gameOverCheck() && game.determineWinner() == 3) //if we have a tie and the last period has ended. Continue the game.
            {
                game.setMaxPeriodNum(game.getMaxPeriodNum() + 1);
            }
            //choose action to do

        }
        System.out.println("\nGame is over");
        System.out.println(homeTeam + " - " + game.getHomeScore() + ", " + awayTeam + " - " + game.getAwayScore());
        System.out.println("Winner: " + game.getWinner());
        System.out.println("\nREPLAY:\n");
        for (int i=0; i<game.getGameLog().size(); i++)
        {
            System.out.println(game.getGameLog().get(i));
        }

    }

}
