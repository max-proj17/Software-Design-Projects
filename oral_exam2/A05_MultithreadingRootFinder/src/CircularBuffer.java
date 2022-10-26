public class CircularBuffer implements Buffer{

    private final double[][] buffer;
    private int occupiedCells = 0;
    private int readIndex = 0;
    private int writeIndex = 0;
    public CircularBuffer(int bufferSize, int elementSize)
    {
        //buffersize determines the rows of the buffer
        //element size determines the size of the input or output value (3 for 3 "tuple" input, 2 for 2 root "tuple" output
        buffer = new double[bufferSize][elementSize];

    }

    @Override
    public synchronized void blockPut(double [] values) throws InterruptedException {
        while(occupiedCells == buffer.length)
        {
            wait();
        }
        buffer[writeIndex] = values;
        writeIndex = (writeIndex + 1) % buffer.length;

        ++occupiedCells;
        notifyAll(); //Tells threads in waiting that next in line can read now.

    }

    @Override
    public synchronized double[] blockGet() throws InterruptedException {

        while(occupiedCells == 0)
        {
            wait();
        }
        double [] values = buffer[readIndex];
        --occupiedCells;
        notifyAll();
        return values;
    }
}
