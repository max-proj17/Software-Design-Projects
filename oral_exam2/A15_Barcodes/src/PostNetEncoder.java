
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class has 4 public methods and one private method.
 * @author Max Finch
 */
public abstract class PostNetEncoder {

    /**
     * HashMap to translate the decimal digits to PostNet binary.
     */
    final static HashMap<Integer, String>  binary_equiv = new HashMap<>();

    /**
     * Sets up the HashMap to be used to encode the zip code
     */
    public static void setup()
    {
        binary_equiv.put(0, "11000"); binary_equiv.put(1, "00011"); binary_equiv.put(2, "00101");
        binary_equiv.put(3, "00110"); binary_equiv.put(4, "01001"); binary_equiv.put(5, "01010");
        binary_equiv.put(6, "01100"); binary_equiv.put(7, "10001"); binary_equiv.put(8, "10010");
        binary_equiv.put(9, "10100");


    }

    /**
     * Computes the checksum digit for the input zipcode
     * @param start the added up digits of the input zip code.
     * @return the checksum digit.
     */
    public static int checkSum(int start) {return 10- (start % 10);}

    /**
     * Creates a '|' and '.' representation of the binary representation of
     * the zip code.
     * @param nums Array of the numbers to be encoded
     * @param length The amount of positions filled in nums
     * @return the '|' and '.' coded String object.
     */
    public static String returnStringCreator (int [] nums, int length)
    {
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("1 ");
        int j = 0;
        while(length != 0)
        {

            returnStr.append(binary_equiv.get(nums[j])).append(" ");
            length--;
            j++;
        }

        returnStr.append("1\n");
        String temp = returnStr.toString();

        for(int i=0; i<temp.length(); i++)
        {
            if(temp.charAt(i) == '0')
            {
                returnStr.append(".");
            }else if(temp.charAt(i) == '1')
            {
                returnStr.append("|");
            }
        }
        return returnStr.toString();
    }

    /**
     * Validates the input String to verify if it is a valid zip code.
     * @return The validated String object.
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
                values = returnStr.split("-");


                if(values.length==3 && values[0].length() == 5 && values[1].length() == 4 && values[2].length() == 2)
                {
                    valid = true;
                }else if(values.length==2 && values[0].length() == 5 && values[1].length() == 4)
                {
                    valid = true;
                }else if(values.length==1 && values[0].length() == 5)
                {
                    valid = true;
                }else
                {
                    throw new Exception("Invalid Zip Code : not in correct format");
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
     * The entire process for Encoding the String from inputValidation. It retrieves the
     * integer value of each character, computes a checkDigit and then uses returnStringCreator
     * to create the '|' and '.' representation.
     * @return The encoded String object.
     */
    public static String encode()
    {
        //Input validation
        setup();
        String zip = inputValidation();
        //First extract numbers from string
        int beforeCheckSum = 0;
        int [] integer_nums = new int [15];
        int position = 0;
        char tmp;
        int amt_in_nums = 0;
        //Because there will only be a max of three strings. Runtime should be closer to O(n)
        for (int i=0; i<zip.length(); i++) {
            tmp = zip.charAt(i);
            if(Character.isDigit(tmp)) {
                beforeCheckSum += Character.getNumericValue(tmp);
                integer_nums[position] = Character.getNumericValue(tmp);
                position++;
                amt_in_nums++;
            }
        }
        //Second, compute the checksum
        integer_nums[position] = checkSum(beforeCheckSum);
        amt_in_nums++;
        //Third, return string of closing full height frame bars, and all binary of the numbers
        //also have a separate line with purely binary
        return returnStringCreator(integer_nums, amt_in_nums);
    }

}
