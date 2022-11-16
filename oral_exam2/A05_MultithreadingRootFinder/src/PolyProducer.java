import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PolyProducer {

    public static void run(Buffer inputCoefficients, Buffer outputRoots, int numInputs) {
        Random rand = new Random();
        double[] rootArray = new double[3];
        int numSet = 1;
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
                System.out.println("Producing coefficients set #" + numSet + " : " + c1 + " " + c2 + " " + c3);
                inputCoefficients.blockPut(new double []{c1,c2,c3});
                rootArray = outputRoots.blockGet();

            }catch (InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            if(rootArray[2] == 1) // 1 signifies 2 real distinct roots
            {
                System.out.println("2 real distinct roots: [root 1 = " + rootArray[0] + ", root 2 = " + rootArray[1] + "]");
            }
            else if(rootArray[2] == 2) //2 signifies two real and equal roots
            {
                System.out.println("2 real and equal roots: [root 1 = root 2 =" + rootArray[0] + "]");
            }
            else { //3 signifies the roots are complex numbers
                System.out.println("Roots are complex and distinct: [Re = " + rootArray[0] + ", Im = " + rootArray[1] + "]");
            }
            numSet++;

        }
        //put values in the input buffer if allowed
        //Read them out in the order they arrive

    }

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

            int numThreads = 10;
            CircularBuffer.setNumToSolve(30);
            inputs = new CircularBuffer(5,"Input Buffer");
            outputs = new CircularBuffer(5,"Output Buffer");
            while(numThreads!=0)
            {
                executorService.execute(new PolyConsumer(inputs, outputs));
                numThreads--;
            }

            run(inputs, outputs, 30);


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
