public class CircularBuffer implements Buffer{

    private final double[][] buffer;
    private int occupiedCells = 0;
    private int readIndex = 0;
    private int writeIndex = 0;
    private final String bufferName;

    private static int numToSolve = 0;

    public CircularBuffer(int bufferSize, String bufferName)
    {
        //buffer size determines the rows of the buffer
        //element size determines the size of the input or output value (3 for 3 "tuple" input, 2 for 2 root "tuple" output
        buffer = new double[bufferSize][3];
        this.bufferName = bufferName;


    }

    public static void setNumToSolve(int numToSolve) {
        CircularBuffer.numToSolve = numToSolve;
    }

    public static int getNumToSolve() {
        return numToSolve;
    }

    @Override
    public synchronized void blockPut(double [] values) throws InterruptedException {

        while (occupiedCells == buffer.length) {
            System.out.println("Waiting for space to open up...");
            wait();
        }

        System.out.println(Thread.currentThread().getName() + " writes..." + "[" + values[0] + ", " + values[1] + ", " + values[2] + "]" + " to " + bufferName);
        buffer[writeIndex] = values;
        writeIndex = (writeIndex + 1) % buffer.length;

        ++occupiedCells;
        System.out.println("\nNum of occupied cells in " + bufferName + ": " + occupiedCells + "\n");

        notifyAll(); //Tells threads in waiting that next in line can read now.

    }

    @Override
    public synchronized double[] blockGet() throws InterruptedException {

        while (occupiedCells == 0) {
            wait();
        }

        double[] values = buffer[readIndex];
        System.out.println(Thread.currentThread().getName() + " reads..." + "[" + values[0] + ", " + values[1] + ", " + values[2] + "]" + " from " + bufferName);
        readIndex = (readIndex + 1) % buffer.length;
        --occupiedCells;

        System.out.println("\nNum of occupied cells in " + bufferName + ": " + occupiedCells + "\n");

        notifyAll();
        return values;

    }
}
