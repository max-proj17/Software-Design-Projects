import java.security.SecureRandom;

public class PolyProducer implements Runnable {


    private final Buffer inputCoefficients;
    private final Buffer outputRoots;
    private final int numpInputs;
    public PolyProducer(Buffer inputCoefficients, Buffer outputRoots, int numInputs) //replace this array with proper buffer object later
    {
        this.inputCoefficients = inputCoefficients;
        this.outputRoots = outputRoots;
        this.numpInputs = numInputs;
    }
    @Override
    public void run() {
        SecureRandom generator = new SecureRandom();
        //randomly generate coefficients here
        for(int i=0; i<numpInputs; i++)
        {

        }
        //put values in the input buffer if allowed
        //Read them out in the order they arrive

    }
}
