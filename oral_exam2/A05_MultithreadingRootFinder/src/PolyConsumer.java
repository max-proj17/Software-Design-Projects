public class PolyConsumer implements Runnable{


    private int processed;
    private final Buffer coefficients;
    private final Buffer outputRoots;
    public PolyConsumer(Buffer coefficients, Buffer outputRoots) //replace this array with proper buffer object later
    {
        this.coefficients = coefficients;
        this.outputRoots = outputRoots;
    }
    @Override
    public void run() {
        final double[] values;
        //add roots to circular buffer afterwards
        try {
            values = coefficients.blockGet();
            double real = -values[1] / (2 * values[0]);
            double img = Math.sqrt(-(values[1] * values[1] - 4 * values[0] * values[2])) / ( 2 * values[0]);
            outputRoots.blockPut(new double[]{real, img}); //add roots to output circular buffer afterwards
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        processed+=1;

    }
}
