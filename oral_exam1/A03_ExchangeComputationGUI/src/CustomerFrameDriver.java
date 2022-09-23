import javax.swing.JFrame;
public class CustomerFrameDriver {

    public static void main (String [] args)
    {
        CustomerFrame customerFrame = new CustomerFrame();
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(500, 400);
        customerFrame.setVisible(true);
    }
}
