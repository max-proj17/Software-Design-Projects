import java.util.Scanner;

public class ScoreLoop {
    private Scanner sc = new Scanner(System.in);
    private int gameOption;
    private String homeTeam;
    private String awayTeam;
    private final Game game;
    public ScoreLoop()
    {
        mainMenuScreen();
        game = selectedGameLoop(gameOption);
    }
    private void mainMenuScreen()
    {

        System.out.println("Select the game you would like to play");
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
                        gameOption = tmp;
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
        }else if(inputType == 3)
        {

        }
    }

    private Game selectedGameLoop(int gameOption)
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

}
