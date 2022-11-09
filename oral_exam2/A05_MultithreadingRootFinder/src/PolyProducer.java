
import java.util.Random;

public class PolyProducer implements Runnable {


    private final Buffer inputCoefficients;
    private final Buffer outputRoots;
    private final int numInputs;
    public PolyProducer(Buffer inputCoefficients, Buffer outputRoots, int numInputs) //replace this array with proper buffer object later
    {
        this.inputCoefficients = inputCoefficients;
        this.outputRoots = outputRoots;
        this.numInputs = numInputs;
    }
    @Override
    public void run() {
        Random rand = new Random();
        double rootArray [] = new double[3];
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
}