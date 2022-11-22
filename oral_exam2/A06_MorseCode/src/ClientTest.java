// Fig. 28.6: ClientTest.java
//(C) Copyright 1992-2018 by Deitel & Associates, Inc. and
// Pearson Education, Inc. All Rights Reserved.


import javax.swing.*;
/**
 * This class has a main method that starts a Client application.
 * I do not own the following code. This is pulled from Fig. 28.6 of
 * the Java How to Program Textbook (C) Copyright 1992-2018 by Deitel
 * and Associates, Inc. and Pearson Education, Inc. All Rights Reserved.
 */
public class ClientTest 
{
   /**
    * Starts the Client application
    * @param args --
    */
   public static void main(String[] args)
   {
      Client application; // declare client application

      // if no command line args
      if (args.length == 0)
         application = new Client("127.0.0.1"); // connect to localhost
      else
         application = new Client(args[0]); // use args to connect

      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runClient(); // run client application
   } 
}


