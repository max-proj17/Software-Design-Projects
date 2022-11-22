// Fig. 28.4: ServerTest.java
//(C) Copyright 1992-2018 by Deitel & Associates, Inc. and
// Pearson Education, Inc. All Rights Reserved.

import javax.swing.*;
/**
 * This class has a main method that starts a Client application.
 * I do not own the following code. This is pulled from Fig. 28.6 of
 * the Java How to Program Textbook (C) Copyright 1992-2018 by Deitel
 * and Associates, Inc. and Pearson Education, Inc. All Rights Reserved.
 */

public class ServerTest
{
   /**
    * Starts Server application
    * @param args --
    */
   public static void main(String[] args)
   {
      Server application = new Server(); // create server
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runServer(); // run server application
   } 
}


