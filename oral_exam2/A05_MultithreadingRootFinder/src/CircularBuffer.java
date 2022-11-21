import java.util.HashMap;

public class CircularBuffer implements Buffer{

    private final double[][] buffer;
    private int occupiedCells = 0;
    private int readIndex = 0;
    private int writeIndex = 0;
    private final String bufferName;

    private static int numToSolve = 0;
    private static int printOutType;
    private final HashMap<String, Integer> thread_poly_count = new HashMap<>();

    public CircularBuffer(int bufferSize, String bufferName, int printOutType)
    {
        //buffer size determines the rows of the buffer
        //element size determines the size of the input or output value (3 for 3 "tuple" input, 2 for 2 root "tuple" output
        buffer = new double[bufferSize][3];
        this.bufferName = bufferName;
        CircularBuffer.printOutType = printOutType;


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
            wait();
        }

            //System.out.println(Thread.currentThread().getName() + " writes..." + "[" + values[0] + ", " + values[1] + ", " + values[2] + "]" + " to " + bufferName);

        buffer[writeIndex] = values;
        writeIndex = (writeIndex + 1) % buffer.length;
        ++occupiedCells;


        notifyAll(); //Tells threads in waiting that next in line can read now.

    }

    public HashMap<String, Integer> getThreadMap() {
        return thread_poly_count;
    }

    @Override
    public synchronized double[] blockGet() throws InterruptedException {

        while (occupiedCells == 0) {
            wait();
        }

        double[] values = buffer[readIndex];
        if(printOutType == 2 && bufferName.equals("Output Buffer"))
        {
            String name_tmp = Thread.currentThread().getName();
            if(thread_poly_count.get(name_tmp) == null)
            {
                thread_poly_count.put(name_tmp, 1);
            }else
            {
                thread_poly_count.put(name_tmp, thread_poly_count.get(name_tmp) + 1);
            }
        }

        readIndex = (readIndex + 1) % buffer.length;
        --occupiedCells;


        notifyAll();
        return values;

    }
}
