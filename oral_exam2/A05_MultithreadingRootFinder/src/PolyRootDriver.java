import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PolyRootDriver {


    public static void main(String [] args)
    {

        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("Select either\n1: Generate and solve 30 sets of randomly generated coefficients");
        System.out.println("2: Generate and solve 30 sets of randomly generated coefficients");
        int selection = inputValidation();

        if(selection == 1)
        {

        } else if (selection == 2) {

        }



        //Buffer inputs = new CircularBuffer();
    }
    public static int inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;
        int selection = 0;
        do {
            try {
                if (sc.hasNextInt()) {
                    selection = Integer.parseInt(sc.nextLine());
                    if(selection == 1 || selection == 2)
                    {
                        valid = true;
                    }else {
                        throw new Exception("Invalid Input");
                    }
                } else {
                    throw new Exception("Invalid Input");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Input");
                sc.nextLine();

            }
        }while(!valid);

        return selection;
    }
}
