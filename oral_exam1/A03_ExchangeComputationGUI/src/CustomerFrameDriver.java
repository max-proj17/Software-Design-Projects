import javax.swing.JFrame;
public class CustomerFrameDriver {

    public static void main (String [] args)
    {
        CustomerFrame customerFrame = new CustomerFrame();
        customerFrame.setResizable(false);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(700, 250);
        customerFrame.setVisible(true);
    }
}
