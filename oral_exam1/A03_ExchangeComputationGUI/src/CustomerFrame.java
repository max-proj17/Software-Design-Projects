
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.awt.event.ItemEvent;
public class CustomerFrame extends JFrame {
    private final JLabel customerInfoLabel;
    private final JComboBox<Customer> customerInfoJComboBox;  //JComponent to select which customer to edit
    private int comboBoxGlobalIndex;
    private final JPanel customerInfoPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JPanel exchangePanel;
    private final JLabel exchangeLabel;
    private final JTextField exchangeField;

    private final JTextField rateField; //test field to change the rate
    private final JPanel ratePanel;
    private final JLabel rateLabel;
    private final Customer[] customers = {
            new Customer(500f, "Marco"),
            new Customer(20.75f, "Bella"),
            new Customer(2f, "Berlin"),
            new Customer(1000.49f, "Jonathan"),
            new Customer(68f, "Tigris"),
            new Customer(1.49f, "Tukar"),
            new Customer(1f, "Jubb"),

    };

    public CustomerFrame()
    {
       super("Customer Frame"); //inherited JFrame constructor
       setLayout(new GridLayout(2,1, 20, 10)); // set frame layout


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
       customerInfoPanel.setLayout(new BoxLayout(customerInfoPanel, BoxLayout.Y_AXIS));
       customerInfoJComboBox = new JComboBox<Customer>(customers);
       customerInfoJComboBox.setFont(new Font("Large", Font.PLAIN, 50));
       customerInfoJComboBox.setMaximumRowCount(3);

       customerInfoPanel.add(customerInfoLabel);
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

       //another anonymous innerclass for an actionListener for rateTextField.
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
                                   customerInfoLabel.setText(customers[0].displayAccount());
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
       exchangeField.addActionListener(new ActionListener() {
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
                           customerInfoLabel.setText(customers[0].displayAccount());

                       }
                   }catch (Exception e)
                   {
                       string = "A number is required";
                   }
               }
               System.out.println("value of string is: " + string);
               JOptionPane.showMessageDialog(null, string);
           }
       });

       topPanel.add(customerInfoPanel);
       topPanel.add(Box.createRigidArea(new Dimension(10,0)));
       topPanel.add(ratePanel);
       bottomPanel.add(exchangePanel);
       bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));

       //display to window
       add(topPanel);
       add(bottomPanel);

    }
}
