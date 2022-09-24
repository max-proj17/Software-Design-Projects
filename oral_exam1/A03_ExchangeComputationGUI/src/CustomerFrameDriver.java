import javax.swing.JFrame;
/**
 * Main driver class that tests CustomerFrame.java
 *
 * @author Max Finch
 */
public class CustomerFrameDriver {
    /**
     * This method will automatically be called when CustomerFrameDriver
     * is run.
     *
     * @param args Command line arguments.
     */
    public static void main (String [] args)
    {
        CustomerFrame customerFrame = new CustomerFrame();
        customerFrame.setResizable(false);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(700, 250);
        customerFrame.setVisible(true);
    }
}
