public class PolyConsumer implements Runnable{


    private int processed;
    private final Buffer coefficients;
    public PolyConsumer(Buffer coefficients) //replace this array with proper buffer object later
    {
        this.coefficients = coefficients;
    }
    @Override
    public void run() {
        final double[] values;
        try {
            values = coefficients.blockGet();
            double real = -values[1] / (2 * values[0]);
            double img = Math.sqrt(-(values[1] * values[1] - 4 * values[0] * values[2])) / ( 2 * values[0]);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        processed+=1;

    }
}
