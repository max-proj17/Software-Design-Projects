import java.io.*;

import java.util.Scanner;

/**
 * This class contains 4 public methods and 2 private methods.
 * @author Max Finch
 */

public abstract class Encryptor {

    /**
     * Array of all letters in the alphabet.
     */
     final static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * This method derives the key from the String input and returns the int array equivalent.
     * @param input Key from user.
     * @return Derived int array from user input.
     */
    public static int[] deriveKey(String input) //input is the string of all n_values
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
     * Takes an input message and a path to the key file and produces a file with
     * the encrypted message
     * @param inputMessage Message to encrypt.
     * @param path PATH to retrieve the key file from.
     * @throws IOException --
     */
    public static void cypheredMessage(String inputMessage, String path) throws IOException {

        //Read in file, retrieve starting index to read N Values from and the values themselves
        FileReader fr;
        BufferedReader in = null;
        try
        {
            fr = new FileReader(path);
            in = new BufferedReader(fr);
        }catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
        assert in != null;
        int key_pos = Integer.parseInt(in.readLine());
        System.out.println("Starting key_pos is: " + key_pos);
        String readValues = in.readLine();
        int [] n_values = deriveKey(readValues);
        int prev_key_pos = key_pos;


        //Create encrypted message from derived N values and use the starting index (key_pos)
        char [] letters = inputMessage.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        int character_index;
        System.out.println("N values read: ");
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
                System.out.println(n_values[key_pos] + " ");
            }
            key_pos = (key_pos + 1) % n_values.length;

        }

        //Create new file with encrypted text and the starting position
        //Edit the key value first line to (this start position) + (length of message)

        File encryptedMsg = makeEncryptedFile();
        FileWriter encryptWriter = new FileWriter(encryptedMsg, false);
        encryptWriter.write(prev_key_pos + "\n" + returnStr);

        encryptWriter.close();

        //Edit key file
        FileWriter keyWriter = new FileWriter(path, false);
        keyWriter.write(key_pos + "\n");
        keyWriter.write(readValues);
        keyWriter.close();

    }

    /**
     * Creates an encrypted file it one doesn't exist already.
     * @return The created file for encryption.
     * @throws IOException --
     */
    private static File makeEncryptedFile() throws IOException {
        File encryptedMsg = new File("oral_exam2/B10_OneTimePad/Files/encrypted.txt");
        if(encryptedMsg.exists())
        {
            System.out.println("File already exists");

        }else {
            if(encryptedMsg.createNewFile())
            {
                System.out.println("Creating file...");
            }
            else {
                System.out.println("Failed to make file");
            }
        }
        return encryptedMsg;
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
            else if(c > alphabet[middle])

            {
                return asciiBinarySearch(c, higher, middle + 1);
            }
            else {
                return asciiBinarySearch(c, middle - 1, lower);
            }
        }
    }
    /**
     * This main method runs the Encryptor program.
     * @param args command-line arguments
     * @throws IOException --
     */
    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String path;
        String message;
        System.out.println("What is the PATH to the key file?");
        path = pathValidation();
        System.out.println("What message would you like to encrypt?");
        message = sc.nextLine();
        cypheredMessage(message, path);
    }

}
