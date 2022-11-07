// Fig. 28.4: ServerTest.java
//(C) Copyright 1992-2018 by Deitel & Associates, Inc. and
// Pearson Education, Inc. All Rights Reserved.

import javax.swing.*;

public class ServerTest
{
   public static void main(String[] args)
   {
      Server application = new Server(); // create server
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runServer(); // run server application
   } 
}


