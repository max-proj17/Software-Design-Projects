
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class CustomerFrame extends JFrame {
    private final JLabel test;
   public CustomerFrame()
   {
       super("Customer Frame"); //inherited JFrame constructor
       setLayout(new FlowLayout()); // set frame layout
       test = new JLabel("Did setToolTipText() work?");
       test.setToolTipText("Test successful");
       add(test);

   }
}
