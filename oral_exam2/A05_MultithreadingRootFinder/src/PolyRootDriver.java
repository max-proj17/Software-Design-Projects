import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PolyRootDriver {


    public static void main(String [] args)
    {
        final Scanner sc = new Scanner(System.in);
        ExecutorService executorService = Executors.newCachedThreadPool();
        boolean valid = false;
        int selection;
        System.out.println("Select either\n1: Generate and solve 30 sets of randomly generated coefficients");
        System.out.println("2: Generate and solve 30 sets of randomly generated coefficients");
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

        //Buffer inputs = new CircularBuffer();
    }
}
