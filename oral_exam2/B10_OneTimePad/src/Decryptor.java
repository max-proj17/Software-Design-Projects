import java.io.*;
import java.util.Scanner;

/**
 * This class contains 4 public methods and 1 private method.
 * @author Max Finch
 */

public abstract class Decryptor {

    /**
     * Array of all letters in the alphabet.
     */
    final static char[] alphabet = {'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N',
        'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};

    /**
     * This method derives the key from the String input and returns the int array equivalent.
     * @param input Key from user.
     * @return Derived int array from user input.
     */
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

    /**
     * Decrypts the encrypted file using the key file. Returns String object of the decrypted message.
     * @param keyPath PATH to the key file.
     * @param encryptPath PATH to the encrypted file.
     * @return String object contained the decrypted message.
     * @throws IOException throws if there is an exception beyond IOException.
     */
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

    /**
     * This binary search method is meant to improve runtime of finding a specific character in the
     * alphabet array. Returns the position of the character as an integer.
     * @param c Character to be found.
     * @param higher Upper bound of the current level of search.
     * @param lower Lower bound of the current level of search.
     * @return Index of the character to be found.
     */
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

    /**
     * Validates the PATH of files.
     * @return A string of a valid PATH.
     */
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

    /**
     * This main method runs the Decryptor program.
     * @param args command-line arguments
     * @throws IOException --
     */
    public static void main(String [] args) throws IOException {

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
