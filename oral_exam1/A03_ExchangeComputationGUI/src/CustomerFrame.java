
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.awt.event.ItemEvent;
/**
 * This class has only a no argument constructor, no methods, 13 private final variables and
 * 1 private variable.
 *
 * @author Max Finch
 */
public class CustomerFrame extends JFrame {
    /**
     * This JLabel is used to display the selected customers Account information by the
     * combo box
     */
    private final JLabel customerInfoLabel;
    /**
     * This JComboBox is used to select the customer that can exchange SWD dollars, delete their account
     * and have their information displayed by customerInfoLabel.
     */
    private final JComboBox<Customer> customerInfoJComboBox;
    /**
     * This int is used to keep track of the customer that's selected. It is utilized
     * in the ItemListener for customerInfoJComboBox, ActionListener for
     * rateField, exchangeField and deleteAccount
     */
    private int comboBoxGlobalIndex;
    /**
     * This JPanel consists of customerInfoLabel and customerInfoJComboBox.
     * This JPanel takes up the top left and top middle of the CustomerFrame.
     */
    private final JPanel customerInfoPanel;
    /**
     * This JPanel houses customerInfoPanel and ratePanel at the top
     *  of the CustomerFrame
     */
    private final JPanel topPanel;
    /**
     * This JPanel houses exchangePanel and deleteAccount(JButton) at the bottom
     * of the CustomerFrame
     */
    private final JPanel bottomPanel;
    /**
     * This JPanel consists of exchangeField and exchangeLabel.
     * This JPanel takes up the bottom left of the CustomerFrame.
     */
    private final JPanel exchangePanel;
    /**
     * This JLabel displays the text "Exchange SWD" underneath the
     * exchangeField.
     */
    private final JLabel exchangeLabel;
    /**
     * This JTextField takes in the amount of SWD that the user wants to
     * exchange from the selected account.
     */
    private final JTextField exchangeField;
    /**
     * This JTextField takes in a new exchange rate provided by the user
     * and changes the rate for all customers in its actionPerformed method
     * by calling Customer.setRate().
     */

    private final JTextField rateField;
    /**
     * This JPanel consists of rateField and rateLabel. This JPanel takes up
     * the top right of the CustomerFrame.
     */

    private final JPanel ratePanel;
    /**
     * This JLabel displays the current exchange rate underneath the
     * rateField.
     */
    private final JLabel rateLabel;
    /**
     * This JButton "deletes" the current selected Customer and
     * triggers a pop-up window showing customer balance in USD.
     */
    private final JButton deleteAccount;
    /**
     * This array contains all the Customers that are created
     * with the CustomerFrame.
     */
    private final Customer[] customers = {
            new Customer(500f, "Marco"),
            new Customer(20.75f, "Bella"),
            new Customer(2f, "Berlin"),
            new Customer(1000.49f, "Jonathan"),
            new Customer(68f, "Tigris"),
            new Customer(1.49f, "Tukar"),
            new Customer(1f, "Jubb"),

    };

    /**
     * No argument constructor
     * Sets the layout to GridLayout with 2 rows, 1 column. The topPanel and bottomPanel
     * will stack on top of each other.
     */
    public CustomerFrame()
    {
       super("Customer Frame"); //inherited JFrame constructor
       setLayout(new GridLayout(2,1));


       //major sections of the application
       topPanel = new JPanel();
       bottomPanel = new JPanel();


       //rate panel setup
       rateField = new JTextField();
       ratePanel = new JPanel();
       rateLabel = new JLabel(("The rate is: " + Customer.getRate()));
       rateLabel.setFont(new Font("Large", Font.PLAIN, 25));
       ratePanel.setLayout(new BoxLayout(ratePanel, BoxLayout.Y_AXIS));
       rateField.setToolTipText("Enter new rate here");
       rateField.setFont(new Font("Large", Font.PLAIN, 50));
       ratePanel.add(rateField);
       ratePanel.add(rateLabel);

       //delete account button setup
       deleteAccount = new JButton("Delete Account");

       //Exchange Window setup
       exchangePanel = new JPanel();
       exchangeField = new JTextField();
       exchangeField.setFont(new Font("Large", Font.PLAIN, 50));
       exchangeLabel = new JLabel("Exchange SWD");
       exchangeLabel.setFont(new Font("Large", Font.PLAIN, 25));
       exchangePanel.setLayout(new BoxLayout(exchangePanel, BoxLayout.Y_AXIS));
       exchangePanel.add(exchangeField);
       exchangePanel.add(exchangeLabel);


        //Customer combobox setup
       customerInfoLabel = new JLabel(customers[0].displayAccount()); //show first Customer initially
       customerInfoPanel = new JPanel();
       customerInfoPanel.setLayout(new BoxLayout(customerInfoPanel, BoxLayout.X_AXIS));
       customerInfoJComboBox = new JComboBox<Customer>(customers);
       customerInfoJComboBox.setFont(new Font("Large", Font.PLAIN, 50));
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
                           comboBoxGlobalIndex = customerInfoJComboBox.getSelectedIndex();
                           customerInfoLabel.setText(customers[customerInfoJComboBox.getSelectedIndex()].displayAccount()); //This SHOULD display toString of selected person
                   }
               }
       );


       rateField.addActionListener(
               new ActionListener() //another anonymous innerclass for an actionListener for rateTextField.
               {
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
                                   customerInfoLabel.setText(customers[comboBoxGlobalIndex].displayAccount());
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
       exchangeField.addActionListener(
               new ActionListener()  //another anonymous innerclass for an actionListener for exchangeField.
               {
                   @Override
                   public void actionPerformed(ActionEvent actionEvent) {
                       String string = "";
                       float num;
                       if(actionEvent.getSource() == exchangeField)
                       {
                           try{
                               num = Float.parseFloat(actionEvent.getActionCommand());
                               if(num <= .01f){
                                   string = "The amount must be greater than .01 SWD coins";
                               }else {

                                   string = customers[comboBoxGlobalIndex].exchangeSWD(num);
                                   customerInfoLabel.setText(customers[comboBoxGlobalIndex].displayAccount());

                               }
                           }catch (Exception e)
                           {
                               string = "A number is required";
                           }
                       }
                       JOptionPane.showMessageDialog(null, string);
                   }
       });

       deleteAccount.addActionListener(
               new ActionListener() //another anonymous innerclass for an actionListener for deleteAccount.
               {
                   @Override
                   public void actionPerformed(ActionEvent actionEvent) {
                       String string = customers[comboBoxGlobalIndex].deleteAccount();
                       JOptionPane.showMessageDialog(null, string);
                   }
               }
       );

       //creates space between customerInfoPanel, customerInfoLabel and ratePanel
       topPanel.add(customerInfoPanel);
       topPanel.add(Box.createRigidArea(new Dimension(10,0)));
       topPanel.add(customerInfoLabel);
       topPanel.add(Box.createRigidArea(new Dimension(10,0)));
       topPanel.add(ratePanel);

       //creates space between exchangePanel and deleteAccount
       bottomPanel.add(exchangePanel);
       bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
       bottomPanel.add(deleteAccount);

       //add to CustomerFrame
       add(topPanel);
       add(bottomPanel);

    }
}
