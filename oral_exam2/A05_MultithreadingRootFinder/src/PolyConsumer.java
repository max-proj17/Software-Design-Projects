public class PolyConsumer implements Runnable{

    private final double[] coefficients;
    private int processed;
    public PolyConsumer(double [] coefficients) //replace this array with proper buffer object later
    {
        this.coefficients = coefficients;
    }
    @Override
    public void run() {

        double real = -coefficients[1] / (2 * coefficients[0]);
        double img = Math.sqrt(-(coefficients[1] * coefficients[1] - 4 * coefficients[0] * coefficients[2])) / ( 2 * coefficients[0]);



    }
}
