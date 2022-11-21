/**
 * This class is an interface with two public methods. It is implemented
 * in the CircularBuffer class.
 * @author Max Finch
 */
public interface Buffer {

    /**
     * A method that when implemented will describe how sets of double
     * values are put inside the buffer
     */
    void blockPut(double [] value) throws InterruptedException;

    /**
     * A method that when implemented will describe how sets of double
     * values are retrieved from the buffer
     */
    double[] blockGet() throws InterruptedException;
}
