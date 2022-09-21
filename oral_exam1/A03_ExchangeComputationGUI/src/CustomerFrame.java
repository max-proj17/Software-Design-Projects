
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.awt.event.ItemEvent;
public class CustomerFrame extends JFrame {
    private final JLabel customerInfoLabel;
    private final JComboBox<GUICustomer> customerInfoJComboBox;  //JComponent to select which customer to edit
    private final JPanel customerInfoPanel;
    private final JTextField rateField; //test field to change the rate
    private final JPanel ratePanel;
    private final JLabel rateLabel;
    private final GUICustomer[] customers = {
            new GUICustomer(500f, "Marco"),
            new GUICustomer(20.75f, "Bella"),
            new GUICustomer(2f, "Berlin"),
            new GUICustomer(1000.49f, "Jonathan"),
            new GUICustomer(68f, "Tigris"),
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

       //rate panel setup
       rateField = new JTextField();
       ratePanel = new JPanel();
       rateLabel = new JLabel(("The rate is: " + Customer.getRate()));
       ratePanel.setLayout(new BoxLayout(ratePanel, BoxLayout.Y_AXIS));
       ratePanel.add(rateField);
       ratePanel.add(rateLabel);

       //Customer combobox setup
       customerInfoPanel = new JPanel();
       customerInfoPanel.setLayout(new BoxLayout(customerInfoPanel, BoxLayout.Y_AXIS));
       customerInfoJComboBox = new JComboBox<GUICustomer>(customers);
       customerInfoJComboBox.setMaximumRowCount(3);
       customerInfoPanel.add(customerInfoJComboBox);
       customerInfoJComboBox.addItemListener(
               new ItemListener() // anonymous inner class
               {
                   @Override
                   public void itemStateChanged(ItemEvent event)
                   {
                       // determine whether item selected
                       if (event.getStateChange() == ItemEvent.SELECTED)
                           customerInfoLabel.setText(customers[customerInfoJComboBox.getSelectedIndex()].superString()); //This SHOULD display toString of selected person
                   }
               }
       );
       //another anonymous innerclass for an actionListener for TextField.
       rateField.addActionListener(
               new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent actionEvent) {
                       String string = "";
                       int num;
                       if(actionEvent.getSource() == rateField)
                       {
                           try{
                               num = Integer.parseInt(actionEvent.getActionCommand());
                               if(num <= 0 || num > 10){
                                   string = "The rate must be between 0 and 10";
                               }else {
                                   Customer.setRate(num);
                                   rateLabel.setText(("The rate is: " + Customer.getRate()));
                                   string = String.format("The new rate is: %s", actionEvent.getActionCommand());
                               }
                           }catch (Exception e)
                           {
                               string = "A number is required";
                           }
                       }
                       JOptionPane.showMessageDialog(null, string);
                   }
               }
       );

       //display to window
       add(customerInfoPanel);
       add(ratePanel);
       customerInfoLabel = new JLabel(customers[0].superString()); //show first Customer initially
       customerInfoLabel.setToolTipText("Combobox Test successful");
       add(customerInfoLabel);

    }
}
