import java.security.SecureRandom;
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

        //randomly generate coefficients here and put into INPUT Circular buffer
        for(int i=0; i<numInputs; i++)
        {
            try
            {
                inputCoefficients.blockPut(new double []{rand.nextInt(2000) - 1000,
                                                         rand.nextInt(2000) - 1000,
                                                         rand.nextInt(2000) - 1000});

            }catch (InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            try
            {
                outputRoots.blockGet();
            }catch(InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }

        }
        //put values in the input buffer if allowed
        //Read them out in the order they arrive

    }
}
