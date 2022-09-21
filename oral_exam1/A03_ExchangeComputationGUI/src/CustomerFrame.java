
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
public class CustomerFrame extends JFrame {
    private final JLabel customerInfoLabel;
    private final JComboBox<GUICustomer> customerJComboBox;  //JComponent to select which customer to edit
    private final GUICustomer[] customers = {
            new GUICustomer(500f, "Marco"),
            new GUICustomer(20.75f, "Bella"),
            new GUICustomer(2f, "Berlin"),
            new GUICustomer(1000.49f, "Jonathan"),
            new GUICustomer(1000.49f, "Jonathan"),
            new GUICustomer(1.49f, "Tukar"),
            new GUICustomer(1f, "Jubb"),

    };
    //only use is to make toString() of a GUICustomer show ONLY THE NAME. super.toString can be used to get the more detailed version
    private class GUICustomer extends Customer{
        public GUICustomer(float startBalance, String name){
            super(startBalance, name);
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
        public String superString()
        {
            return super.toString();
        }
    }
    public CustomerFrame()
    {
       super("Customer Frame"); //inherited JFrame constructor
       setLayout(new FlowLayout()); // set frame layout
       customerJComboBox = new JComboBox<GUICustomer>(customers);
       customerJComboBox.setMaximumRowCount(3);


       customerJComboBox.addItemListener(
               new ItemListener() // anonymous inner class
               {
                   @Override
                   public void itemStateChanged(ItemEvent event)
                   {
                       // determine whether item selected
                       if (event.getStateChange() == ItemEvent.SELECTED)
                           customerInfoLabel.setText(customers[customerJComboBox.getSelectedIndex()].superString()); //This SHOULD display toString of selected person
                   }
               }
       );
       add(customerJComboBox);
       customerInfoLabel = new JLabel(customers[0].superString()); //show first Customer
       customerInfoLabel.setToolTipText("Combobox Test successful");
       add(customerInfoLabel);

    }
}
