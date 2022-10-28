import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PolyRootDriver {


    public static void main(String [] args) throws InterruptedException
    {

        ExecutorService executorService = Executors.newCachedThreadPool();


        System.out.println("Select either\n1: Generate and solve 30 sets of randomly generated coefficients");
        System.out.println("2: Generate and solve 3000 sets of randomly generated coefficients");
        int selection = inputValidation();
        Buffer inputs;
        Buffer outputs;

        if(selection == 1)
        {
            inputs = new CircularBuffer(30, 3);
            outputs = new CircularBuffer(30, 3);
            executorService.execute(new PolyProducer(inputs, outputs, 30));
            executorService.execute(new PolyConsumer(inputs, outputs));

        } else if (selection == 2) {

        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

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
