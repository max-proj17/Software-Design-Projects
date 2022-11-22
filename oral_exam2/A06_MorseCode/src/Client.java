// Fig. 28.5: Client.java
//(C) Copyright 1992-2018 by Deitel & Associates, Inc. and
// Pearson Education, Inc. All Rights Reserved.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
/**
 * This class has a main method that defines a Client application.
 *
 * I do not own the following code. This is pulled from Fig. 28.5 of
 * the Java How to Program Textbook (C) Copyright 1992-2018 by Deitel
 * & Associates, Inc. and Pearson Education, Inc. All Rights Reserved.
 */
public class Client extends JFrame 
{
   /**
    * enters information from user
    */
   private final JTextField enterField; // enters information from user
   /**
    * display information to user
    */
   private final JTextArea displayArea; // display information to user
   /**
    * output stream to server
    */
   private ObjectOutputStream output; // output stream to server
   /**
    * input stream from server
    */
   private ObjectInputStream input; // input stream from server
   /**
    *  message from server
    */
   private String message = ""; // message from server
   /**
    * host server for this application
    */
   private final String chatServer; // host server for this application
   /**
    * socket to communicate with server
    */
   private Socket client; // socket to communicate with server

   // initialize chatServer and set up GUI

   /**
    * One argument constructor
    * @param host determines the server the client connects to.
    */
   public Client(String host)
   {
      super("Client");

      chatServer = host; // set server to which this client connects

      enterField = new JTextField(); // create enterField
      enterField.setEditable(false);
      enterField.addActionListener(
         new ActionListener() 
         {
            // send message to server
            public void actionPerformed(ActionEvent event)
            {
               sendData(event.getActionCommand());
               enterField.setText("");
            } 
         } 
      ); 

      add(enterField, BorderLayout.NORTH);

      displayArea = new JTextArea(); // create displayArea
      add(new JScrollPane(displayArea), BorderLayout.CENTER);

      setSize(300, 150); // set size of window
      setVisible(true); // show window
   }

   // connect to server and process messages from server

   /**
    * Processes input and output streams until the Client terminates the connection.
    */
   public void runClient() 
   {
      try // connect to server, get streams, process connection
      {
         connectToServer(); // create a Socket to make connection
         getStreams(); // get the input and output streams
         processConnection(); // process connection
      } 
      catch (EOFException eofException) 
      {
         displayMessage("\nClient terminated connection");
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
      finally 
      {
         closeConnection(); // close connection
      }
   }

   // connect to server

   /**
    * Connects to the IP address of the server and port number.
    * @throws IOException throws if the server with the entered port is being used or not found
    */
   private void connectToServer() throws IOException
   {      
      displayMessage("Attempting connection\n");

      // create Socket to make connection to server
      client = new Socket(InetAddress.getByName(chatServer), 23759);

      // display connection information
      displayMessage("Connected to: " + 
         client.getInetAddress().getHostName());
   }

   // get streams to send and receive data

   /**
    * Get streams to send and receive data.
    * @throws IOException throws if no streams can be received
    */
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream(client.getOutputStream());      
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream(client.getInputStream());

      displayMessage("\nGot I/O streams\n");
   }

   // process connection with server

   /**
    * Retrieves messages from the Server and displays them.
    * @throws IOException throws if an error occurs in reading the input object.
    */
   private void processConnection() throws IOException
   {
      // enable enterField so client user can send messages
      setTextFieldEditable(true);

      do // process messages sent from server
      { 
         try // read message and display it
         {
            message = (String) input.readObject(); // read new message
            displayMessage("\n" + message); // display message
         } 
         catch (ClassNotFoundException classNotFoundException) 
         {
            displayMessage("\nUnknown object type received");
         } 

      } while (!message.equals("SERVER>>> TERMINATE"));
   }

   // close streams and socket

   /**
    * Closes streams and socket between client and server.
    */
   private void closeConnection() 
   {
      displayMessage("\nClosing connection");
      setTextFieldEditable(false); // disable enterField

      try 
      {
         output.close(); // close output stream
         input.close(); // close input stream
         client.close(); // close socket
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }

   // send message to server

   /**
    * Sends messages to the server.
    * @param message the message to be sent.
    */
   private void sendData(String message)
   {
      try // send object to server
      {
         output.writeObject("CLIENT>>> " + message);
         output.flush(); // flush data to output
         displayMessage("\nCLIENT>>> " + message);
      } 
      catch (IOException ioException)
      {
         displayArea.append("\nError writing object");
      } 
   }

   // manipulates displayArea in the event-dispatch thread

   /**
    * Manipulates displayArea in the event-dispatch thread
    * @param messageToDisplay the message to be displayed
    */
   private void displayMessage(final String messageToDisplay)
   {
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() // updates displayArea
            {
               displayArea.append(messageToDisplay);
            }
         }  
      ); 
   } 

   // manipulates enterField in the event-dispatch thread

   /**
    * Manipulates enterField in the event-dispatch thread
    * @param editable determines whether the text-field is editable.
    */
   private void setTextFieldEditable(final boolean editable)
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // sets enterField's editability
            {
               enterField.setEditable(editable);
            }
         } 
      ); 
   } 
}



