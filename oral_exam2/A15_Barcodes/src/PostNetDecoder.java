
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class has three public methods and one private method.
 * @author Max Finch
 */

public abstract class PostNetDecoder {
    /**
     * HashMap to translate the PostNet binary to decimal digits
     */
    final static HashMap<String, Integer> binary_equiv = new HashMap<>();

    /**
     * Sets up the HashMap to be used to decode the PostNet code
     */
    public static void setup()
    {
        binary_equiv.put("11000", 0); binary_equiv.put("00011", 1); binary_equiv.put("00101", 2);
        binary_equiv.put("00110", 3); binary_equiv.put("01001", 4); binary_equiv.put("01010", 5);
        binary_equiv.put("01100", 6); binary_equiv.put("10001", 7); binary_equiv.put("10010", 8);
        binary_equiv.put("10100", 9);


    }

    /**
     * Enters a validation loop to retrieve a valid PostNet code.
     * @return String object of the valid input PostNet code
     */
    private static String inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;

        String [] values;


        String returnStr = "";

        do {
            try {
                returnStr = sc.nextLine();
                values = returnStr.split(" ");
                System.out.println(values.length);

                if(returnStr.charAt(0) != 1 && returnStr.charAt(returnStr.length()-1)!='1')
                {
                    throw new Exception("Invalid Zip Code : no frame bar provided");
                }else if (values.length == 8 || values.length == 12 || values.length == 14)
                {
                    valid = true;

                } else
                {
                    throw new Exception("Invalid Zip Code : not enough binary digits");
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while(!valid);
        return returnStr;

    }

    /**
     * Decodes the split input String and returns a String object.
     * @param nums the split input message from the user.
     * @return the decoded message.
     */
    public static String decode(String [] nums)
    {

        // Go through each String in array and grab numerical value from hashmap
        // - once 6 digits are used or DPC is used add a " - "
        StringBuilder binary_rep = new StringBuilder();
        for(int i=1; i<nums.length-2; i++)
        {

            if((i == 6 || i == 10))
            {
                binary_rep.append("-");
            }
            binary_rep.append(binary_equiv.get(nums[i]));
        }

        // - return the String when built
        return binary_rep.toString();
    }

    /**
     * A single function used to trigger the decoding program.
     * @return the decoded message.
     */
    public static String process()
    {
        setup();
        // Make array of the regex of only numbers
        return decode(inputValidation().split(" "));
    }
}
