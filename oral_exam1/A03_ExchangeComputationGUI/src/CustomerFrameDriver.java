import javax.swing.JFrame;
public class CustomerFrameDriver {

    public static void main (String [] args)
    {
        CustomerFrame customerFrame = new CustomerFrame();
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(520, 260);
        customerFrame.setVisible(true);
    }
}
