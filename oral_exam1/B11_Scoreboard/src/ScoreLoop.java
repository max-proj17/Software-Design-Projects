import java.util.Scanner;

public class ScoreLoop {
    private Scanner sc = new Scanner(System.in);
    private int gameOption = 0;
    private String homeTeam;
    private String awayTeam;
    public ScoreLoop()
    {
        menuScreen();
    }
    private void menuScreen()
    {
        String homeTeam;
        String awayTeam;

        System.out.println("Select the game you would like to play");
        System.out.println("1. Soccer");
        System.out.println("2. Basketball");
        System.out.println("3. Baseball");
        System.out.println("4. Football");
        System.out.println("5. Hockey");

        validInput(1);

        System.out.println("Enter Home Team: ");
        validInput(2);
        System.out.println("Enter Away Team: ");
        validInput(2);

    }
    private void validInput(int inputType)
    {
        //Input type 1: Game selection validation
        //Input type 2: Team name validation
        //Input type 3: Game choice validation
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
                    homeTeam = sc.nextLine();

                }else{

                    System.out.println("Invalid Input. Team name must contain letters.");
                    sc.nextLine();
                }
            }while(!valid);
        }
    }

}
