// Fig. 28.3: Server.java
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
/**
 * This class has a main method that defines a Client application.
 *
 * I do not own the following code. This is pulled from Fig. 28.5 of
 * the Java How to Program Textbook (C) Copyright 1992-2018 by Deitel
 * & Associates, Inc. and Pearson Education, Inc. All Rights Reserved.
 */
public class Server extends JFrame 
{
   private final JTextField enterField; // inputs message from user
   private final JTextArea displayArea; // display information to user
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
      String message = "Connection successful. Please send me a message in English or Morse code. \n" +
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
            message = message.substring(10);
            String returnMsg = deriveMessage(message);
            sendData(returnMsg);
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
      if(input.charAt(0) == '-' || input.charAt(0) == '.')
      {
         displayMessage("\nMorse to English");
         return englishFromMorse(input);
      }else
      {
         displayMessage("\nEnglish to Morse");
         return morseFromEnglish(input);
      }

   }
   private String englishFromMorse(String input)
   {
      StringBuilder returnStr = new StringBuilder();

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
      morseToEnglish.put("---..", "8"); morseToEnglish.put("----.", "9"); morseToEnglish.put("-----", "0");
      String [] tokens = input.split(" ");
      for(int i=0; i<tokens.length; i++)   //convert into sentence format
      {
         if(tokens[i].length() != 0)
         {
            if(morseToEnglish.get(tokens[i]) != null)
            {
               returnStr.append(morseToEnglish.get(tokens[i]));
            }else
            {
               returnStr.append("?");
            }

         }else if(tokens[i].length() == 0 && tokens[i + 1].length() == 0)
         {
            returnStr.append(" ");
            i++;
         }
      }

      return returnStr.toString();
   }
   private String morseFromEnglish(String input)
   {
      StringBuilder returnStr = new StringBuilder();

      HashMap<String, String> englishToMorse = new HashMap<>();
      englishToMorse.put("A", ".-"); englishToMorse.put("B","-..."); englishToMorse.put("C","-.-.");
      englishToMorse.put("D","-.."); englishToMorse.put("E","."); englishToMorse.put("F","..-.");
      englishToMorse.put("G","--."); englishToMorse.put("H","...."); englishToMorse.put("I","..");
      englishToMorse.put("J",".---"); englishToMorse.put("K","-.-"); englishToMorse.put("L",".-..");
      englishToMorse.put("M", "--"); englishToMorse.put("N","-."); englishToMorse.put("O","---");
      englishToMorse.put("P", ".--."); englishToMorse.put("Q","--.-"); englishToMorse.put("R",".-.");
      englishToMorse.put("S","..."); englishToMorse.put("T","-"); englishToMorse.put("U","..-");
      englishToMorse.put("V","...-"); englishToMorse.put("W",".--"); englishToMorse.put("X", "-..-");
      englishToMorse.put("Y","-.--"); englishToMorse.put("Z","--.."); englishToMorse.put("1",".----");
      englishToMorse.put("2","..---"); englishToMorse.put("3","...--"); englishToMorse.put("4", "....-");
      englishToMorse.put("5","....."); englishToMorse.put("6","-...."); englishToMorse.put("7", "--...");
      englishToMorse.put("8","---.."); englishToMorse.put("9","----."); englishToMorse.put("0", "-----");
      char[] tokens = input.toCharArray();

      //convert into sentence format
      for (char token : tokens) {
         if (token != ' ') {
            String singleChar = Character.toString(token).toUpperCase();
            System.out.println(singleChar);
            if (englishToMorse.get(singleChar) != null) {
               returnStr.append(englishToMorse.get(singleChar));
               returnStr.append(" ");
            } else {
               returnStr.append("?");
            }

         } else {
            returnStr.append("   ");

         }
      }

      return returnStr.toString();

   }
} 



