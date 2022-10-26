public class PolyProducer implements Runnable {


    private final Buffer inputCoefficients;
    private final Buffer outputRoots;
    public PolyProducer(Buffer inputCoefficients, Buffer outputRoots) //replace this array with proper buffer object later
    {
        this.inputCoefficients = inputCoefficients;
        this.outputRoots = outputRoots;
    }
    @Override
    public void run() {

        //randomly generate coefficients here
        //put values in the input buffer if allowed
        //Read them out in the order they arrive

    }
}
