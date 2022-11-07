import java.util.Scanner;

public class OneTimePadDriver {

    public static void main (String [] args)
    {
        System.out.println("Give me a message to encrypt");
        inputValidation();
        //get message
        //get key
        //generate script with key
    }
    public static void inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;
        do {
            try {
                if (!sc.hasNextInt()) {
                    valid = true;
                } else {
                    throw new Exception("Invalid Input");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Input");
                sc.nextLine();

            }
        }while(!valid);

    }
}
