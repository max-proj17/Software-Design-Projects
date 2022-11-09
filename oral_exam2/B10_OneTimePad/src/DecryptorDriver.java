import java.util.Arrays;
import java.util.Scanner;

public class DecryptorDriver {


    public static void main (String [] args)
    {
        System.out.println("Give me a message to decrypt");
        String cypheredMessage = inputValidation();
        System.out.println("Enter a key in the following format : 2,3,4,16,3,2");
        int [] key = Decryptor.deriveKey(inputValidation());
        System.out.println("Decrypted Text");
        System.out.println(Decryptor.decrypt(cypheredMessage, key));
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
