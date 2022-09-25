import java.util.Scanner;

public class ScoreLoop {
    public ScoreLoop()
    {
        menuScreen();
    }
    private static void menuScreen()
    {
        int gameOption;
        Boolean valid = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the game you would like to play");
        System.out.println("1. Soccer");
        System.out.println("2. Basketball");
        System.out.println("3. Baseball");
        System.out.println("4. Football");
        System.out.println("5. Hockey");

        do {
            System.out.println("Enter Choice: ");
            if (sc.hasNextInt()){
                gameOption = sc.nextInt();
                valid = true;
            }else {

                System.out.println("Invalid Input");
                sc.nextLine();
            }
        }while(!valid);
    }

}
