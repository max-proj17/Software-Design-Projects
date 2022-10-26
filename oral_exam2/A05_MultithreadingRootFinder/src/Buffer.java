public interface Buffer {

    public void blockPut(double [] value) throws InterruptedException;

    public double[] blockGet() throws InterruptedException;
}
