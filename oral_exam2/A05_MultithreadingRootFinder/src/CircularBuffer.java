import java.util.HashMap;

/**
 * This class has a three-argument constructor and six public methods
 *
 * @author Max Finch
 */
public class CircularBuffer implements Buffer{

    /**
     * This variable represents the buffer itself.
     * It stores arrays of doubles.
     */
    private final double[][] buffer;
    /**
     *  Keeps track of the number of occupied cells in the buffer.
     */
    private int occupiedCells = 0;
    /**
     * Keeps track of the reading index which changes as values are retrieved
     * from the buffer.
     */
    private int readIndex = 0;
    /**
     * Keeps track of the writing index which changes as values are inserted
     * into the buffer.
     */
    private int writeIndex = 0;
    /**
     * Keeps the name of the CircularBuffer object. Used to determine
     * if thread_poly_count should be used or not.
     */
    private final String bufferName;
    /**
     * Shared across all instances of CircularBuffer, this int variable
     * makes sure all sets of coefficients are solved.
     */
    private static int numToSolve = 0;
    /**
     * This variable along with bufferName determine whether thread_poly_count
     * is used.
     */
    private static int printOutType;
    /**
     * This HashMap is used to keep track of each thread's contribution to
     * solving the selected number of coefficients.
     */
    private final HashMap<String, Integer> thread_poly_count = new HashMap<>();

    /**
     * Three-argument constructor
     * @param bufferSize determines the rows of the CircularBuffer
     * @param bufferName keeps the name of the CircularBuffer
     * @param printOutType int value that determines if thread_poly_count is used
     */
    public CircularBuffer(int bufferSize, String bufferName, int printOutType)
    {
        //buffer size determines the rows of the buffer

        buffer = new double[bufferSize][3];
        this.bufferName = bufferName;
        CircularBuffer.printOutType = printOutType;


    }

    /**
     * sets the amount of sets of coefficients that need to be solved.
     * @param numToSolve numerical value of description above.
     */
    public static void setNumToSolve(int numToSolve) {
        CircularBuffer.numToSolve = numToSolve;
    }

    /**
     * returns the amount of sets of coefficients that need to be solved.
     * @return numToSolve numerical value of description above.
     */

    public static int getNumToSolve() {
        return numToSolve;
    }

    /**
     * This synchronized method inserts an array of double values when
     * no other thread is trying to access the method on the same CircularBuffer
     * object.
     * @param values an array of double values
     * @throws InterruptedException thrown when thread is waiting, sleeping or occupied.
     */
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

    /**
     * returns the HashMap containing how many sets of coefficients each thread processed.
     * @return the HashMap described above.
     */
    public HashMap<String, Integer> getThreadMap() {
        return thread_poly_count;
    }

    /**
     * This synchronized method returns an array of double values when
     * no other thread is trying to access the method on the same CircularBuffer
     * object.
     * @return an array of double values
     * @throws InterruptedException thrown when thread is waiting, sleeping or occupied.
     */
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
