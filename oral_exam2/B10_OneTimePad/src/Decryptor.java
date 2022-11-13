import java.io.*;
import java.util.Scanner;

public class Decryptor {


    final static char[] alphabet = {'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N',
        'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};

    public static int[] deriveKey(String input)
    {
        String [] strValues = input.split(",");
        int[] values = new int[strValues.length];
        int i = 0;
        for(String value: strValues)
        {
            values[i] = Integer.parseInt(value);
            i++;
        }
        return values;
    }
    public static String decrypt(String keyPath, String encryptPath) throws IOException {
        //Read in keyPath file then encryptPath file
        BufferedReader in_key = null;
        BufferedReader in_encrypt = null;
        try
        {
            in_key = new BufferedReader(new FileReader(keyPath));
            in_encrypt = new BufferedReader(new FileReader(encryptPath));
        }catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }

        //get N values only
        assert in_key != null;
        in_key.readLine();
        String readValues = in_key.readLine();
        int [] n_values = deriveKey(readValues);

        //get message and key position
        assert in_encrypt != null;
        int key_pos = Integer.parseInt(in_encrypt.readLine());
        String message = in_encrypt.readLine();



        char [] letters = message.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        int character_index;
        for(char c: letters)
        {

            character_index = asciiBinarySearch(Character.toUpperCase(c), 25, 0);

            if(character_index == -1)
            {
                returnStr.append(' ');
            }else {
                // 1. Location in alphabet array  2. Value to be added to corresponding char 3. Length of alphabet array
                character_index = (character_index + n_values[key_pos]) % alphabet.length;
                returnStr.append(alphabet[character_index]);
            }
            key_pos = (key_pos + 1) % n_values.length;

        }
        return returnStr.toString();

    }
    private static int asciiBinarySearch(char c, int higher, int lower)
    {

        if(lower > higher)
        {
            return -1;
        }
        else {
            int middle = (lower + higher)/2;
            if (c == alphabet[middle])
            {
                return middle;
            }
            else if(c < alphabet[middle])

            {
                return asciiBinarySearch(c, higher, middle + 1);
            }
            else {
                return asciiBinarySearch(c, middle - 1, lower);
            }
        }
    }
    public static String pathValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String returnStr = "";

        do {
            try {
                returnStr = sc.nextLine();
                File path = new File(returnStr);

                if(!path.exists())
                {
                    throw new Exception("Invalid PATH");
                }else
                {
                    valid = true;
                }

            }catch (Exception e)
            {
                System.out.println("Invalid PATH");
                sc.nextLine();

            }
        }while(!valid);
        return returnStr;
    }
    public static void main(String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String keyPath;
        String encryptedPath;
        System.out.print("Enter location of key file: ");
        keyPath = pathValidation();
        System.out.print("\nEnter location of encrypted file: ");
        encryptedPath = pathValidation();
        String message = decrypt(keyPath, encryptedPath);
        System.out.println("Decrypted message: " + message);
    }
}
