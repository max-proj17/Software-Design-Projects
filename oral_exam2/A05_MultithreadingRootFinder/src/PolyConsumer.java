import java.security.SecureRandom;

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
        double r1;
        double r2;
        //add roots to circular buffer afterwards
        try {
            values = coefficients.blockGet();

            double det = (values[1]*values[1])-4*values[0]*values[2];
            if(det > 0) //if it produces 2 different real roots
            {
                r1 = (-values[1] + Math.sqrt(det)) / (2*values[0]);
                r2 = (-values[1] - Math.sqrt(det)) / (2*values[0]);
                outputRoots.blockPut(new double[]{r1, r2, 1}); // 1 signifies 2 real distinct roots
            }else if(det == 0)
            {
                r1 = -values[1] / (2*values[0]);
                outputRoots.blockPut(new double[]{r1, 0, 2}); //2 signifies two real and equal roots
            }else{
                double real = -values[1] / (2 * values[0]);
                double img = Math.sqrt(-det) / ( 2 * values[0]);
                outputRoots.blockPut(new double[]{real, img, 3}); //3 signifies the roots are complex numbers
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        processed+=1;

    }
}
