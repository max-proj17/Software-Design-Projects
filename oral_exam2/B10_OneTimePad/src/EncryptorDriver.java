import java.util.Arrays;
import java.util.Scanner;

public class EncryptorDriver {


    public static void main (String [] args)
    {
        System.out.println("Give me a message to encrypt");
        String message = inputValidation();
        int [] key = Encryptor.generateNValues(message.length());
        System.out.println(message);
        System.out.println(Arrays.toString(key));
        System.out.println(Encryptor.cypheredMessage(message, key));
        //get message
        //get key
        //generate script with key
    }
    public static String inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;
        do {
            try {
                if (!sc.hasNextInt()) {
                    valid = true;

                } else {
                    throw new Exception("Invalid Input");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Input");
                sc.nextLine();

            }
        }while(!valid);
        return sc.nextLine();
    }
}
