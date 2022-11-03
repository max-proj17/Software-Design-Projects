// Fig. 28.3: Server.java
// Server portion of a client/server stream-socket connection. 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends JFrame 
{
   private JTextField enterField; // inputs message from user
   private JTextArea displayArea; // display information to user
   private ObjectOutputStream output; // output stream to client
   private ObjectInputStream input; // input stream from client
   private ServerSocket server; // server socket
   private Socket connection; // connection to client
   private int counter = 1; // counter of number of connections

   // set up GUI
   public Server()
   {
      super("Server");

      enterField = new JTextField(); // create enterField
      enterField.setEditable(false);
      enterField.addActionListener(
         new ActionListener() 
         {
            // send message to client
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

   // set up and run server 
   public void runServer()
   {
      try // set up server to receive connections; process connections
      {
         server = new ServerSocket(23759, 100); // create ServerSocket

         while (true) 
         {
            try 
            {
               waitForConnection(); // wait for a connection
               getStreams(); // get input & output streams
               processConnection(); // process connection
            } 
            catch (EOFException eofException) 
            {
               displayMessage("\nServer terminated connection");
            } 
            finally 
            {
               closeConnection(); //  close connection
               ++counter;
            } 
         } 
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }

   // wait for connection to arrive, then display connection info
   private void waitForConnection() throws IOException
   {
      displayMessage("Waiting for connection\n");
      connection = server.accept(); // allow server to accept connection            
      displayMessage("Connection " + counter + " received from: " +
         connection.getInetAddress().getHostName());
   }

   // get streams to send and receive data
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream(connection.getOutputStream());
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream(connection.getInputStream());

      displayMessage("\nGot I/O streams\n");
   }

   // process connection with client
   private void processConnection() throws IOException
   {
      String message = "Connection successful. Please send me a message in English or Morse code. " +
              "Type \"CLIENT>>> TERMINATE\" to terminate our connection.";
      sendData(message); // send connection successful message

      // enable enterField so server user can send messages
      setTextFieldEditable(true);

      do // process messages sent from client
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

      } while (!message.equals("CLIENT>>> TERMINATE"));
   }

   // close streams and socket
   private void closeConnection() 
   {
      displayMessage("\nTerminating connection\n");
      setTextFieldEditable(false); // disable enterField

      try 
      {
         output.close(); // close output stream
         input.close(); // close input stream
         connection.close(); // close socket
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }

   // send message to client
   private void sendData(String message)
   {
      try // send object to client
      {
         output.writeObject("SERVER>>> " + message);
         output.flush(); // flush output to client
         displayMessage("\nSERVER>>> " + message);
      } 
      catch (IOException ioException) 
      {
         displayArea.append("\nError writing object");
      } 
   }

   // manipulates displayArea in the event-dispatch thread
   private void displayMessage(final String messageToDisplay)
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // updates displayArea
            {
               displayArea.append(messageToDisplay); // append message
            } 
         } 
      ); 
   } 

   // manipulates enterField in the event-dispatch thread
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
   private String deriveMessage(String input)
   {
      String returnStr = "";
      if(input.charAt(0) == '.' || input.charAt(0) == '-')
      {
         HashMap<String, String> morseToEnglish = new HashMap<>();
         morseToEnglish.put(".-", "A"); morseToEnglish.put("-...", "B"); morseToEnglish.put("-.-.", "C");
         morseToEnglish.put("-..", "D"); morseToEnglish.put(".", "E"); morseToEnglish.put("..-.", "F");
         morseToEnglish.put("--.", "G"); morseToEnglish.put("....", "H"); morseToEnglish.put("..", "I");
         morseToEnglish.put(".---", "J"); morseToEnglish.put("-.-", "K"); morseToEnglish.put(".-..", "L");
         morseToEnglish.put("--", "M"); morseToEnglish.put("-.", "N"); morseToEnglish.put("---", "O");
         morseToEnglish.put(".--.", "P"); morseToEnglish.put("--.-", "Q"); morseToEnglish.put(".-.", "R");
         morseToEnglish.put("...", "S"); morseToEnglish.put("-", "T"); morseToEnglish.put("..-", "U");
         morseToEnglish.put("...-", "V"); morseToEnglish.put(".--", "W"); morseToEnglish.put("-..-", "X");
         morseToEnglish.put("-.--", "Y"); morseToEnglish.put("--..", "Z"); morseToEnglish.put(".----", "1");
         morseToEnglish.put("..---", "2"); morseToEnglish.put("...--", "3"); morseToEnglish.put("....-", "4");
         morseToEnglish.put(".....", "5"); morseToEnglish.put("-....", "6"); morseToEnglish.put("--...", "7");
         morseToEnglish.put("---..", "8"); morseToEnglish.put("----.", "9"); morseToEnglish.put("-----", "9");
         for(int i=0; i<input.length(); i++)
         {

         }

      }else{
         HashMap<String, String> englishToMorse = new HashMap<>();
      }


      return returnStr;
   }
} 

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/