public interface Buffer {

    public void blockPut(int value) throws InterruptedException;

    public int blockGet() throws InterruptedException;
}
