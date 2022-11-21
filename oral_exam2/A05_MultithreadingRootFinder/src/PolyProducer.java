import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class has a main method and 2 public methods.
 */

public class PolyProducer {
    /**
     * This method creates numImputs amount of coefficient sets, puts them in the
     * inputCoefficients CircularBuffer, and waits to retrieve the roots from the outputRoots
     * CircularBuffer.
     * @param inputCoefficients the input CircularBuffer used by PolyProducer and PolyConsumers.
     * @param outputRoots the output CircularBuffer used by PolyProducer and PolyConsumers.
     * @param numInputs determines how many coefficient sets to produce;
     * @param selection determines if certain printouts are executed.
     */

    public static void run(CircularBuffer inputCoefficients, CircularBuffer outputRoots, int numInputs, int selection) {
        Random rand = new Random();
        double[] rootArray = new double[3];

        int c1;
        int c2;
        int c3;
        //randomly generate coefficients here and put into INPUT Circular buffer
        for(int i=0; i<numInputs; i++)
        {
            try
            {
                c1 = rand.nextInt(2000) - 1000;
                c2 = rand.nextInt(2000) - 1000;
                c3 = rand.nextInt(2000) - 1000;
                inputCoefficients.blockPut(new double []{c1,c2,c3});
                rootArray = outputRoots.blockGet();

            }catch (InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            if(selection == 1) {
                if (rootArray[2] == 1) // 1 signifies 2 real distinct roots
                {
                    System.out.println((i+1) +". " + "2 real distinct roots: [root 1 = " + rootArray[0] + ", root 2 = " + rootArray[1] + "]");
                } else if (rootArray[2] == 2) //2 signifies two real and equal roots
                {
                    System.out.println((i+1) +". " + "2 real and equal roots: [root 1 = root 2 =" + rootArray[0] + "]");
                } else { //3 signifies the roots are complex numbers
                    System.out.println((i+1) +". " + "Roots are complex and distinct: [Re = " + rootArray[0] + ", Im = " + rootArray[1] + "]");
                }
            }

        }
        if(selection == 2) {
            HashMap<String, Integer> tmp = outputRoots.getThreadMap();
            for (String key : tmp.keySet()) {
                System.out.println(key + " solved " + tmp.get(key) + " sets of coefficients");
            }
        }
        //put values in the input buffer if allowed
        //Read them out in the order they arrive

    }

    /**
     * This main method allows the user to compute 30 sets or 3000 sets of coefficients.
     * @param args ---
     */
    public static void main(String [] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();


        System.out.println("Select either\n1: Generate and solve 30 sets of randomly generated coefficients");
        System.out.println("2: Generate and solve 3000 sets of randomly generated coefficients");
        int selection = inputValidation();
        CircularBuffer inputs;
        CircularBuffer outputs;

        if(selection == 1)
        {

            int numThreads = 10;
            CircularBuffer.setNumToSolve(30);
            inputs = new CircularBuffer(5,"Input Buffer", 1);
            outputs = new CircularBuffer(5,"Output Buffer", 1);
            while(numThreads!=0)
            {
                executorService.execute(new PolyConsumer(inputs, outputs));
                numThreads--;
            }

            run(inputs, outputs, 30, 1);


        } else if (selection == 2) {
            int numThreads = 10;
            CircularBuffer.setNumToSolve(3000);
            inputs = new CircularBuffer(5,"Input Buffer", 2);
            outputs = new CircularBuffer(5,"Output Buffer", 2);
            while(numThreads!=0)
            {
                executorService.execute(new PolyConsumer(inputs, outputs));
                numThreads--;
            }

            run(inputs, outputs, 3000, 2);
        }

        executorService.shutdown();
        System.exit(1);



        //Buffer inputs = new CircularBuffer();
    }

    /**
     * This method validates the input so that the task executed is based
     * of the number 1 or 2
     * @return number selected
     */
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
