import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains two public methods and one private method.
 * @author Max Finch
 */
public abstract class UPCADecoder {

    /**
     * HashMap containing the w-b-w-b pattern to decimal digit equivalent.
     */
    final static HashMap<String, Integer> upc_w_b = new HashMap<>();
    /**
     * HashMap containing the b-w-b-w pattern to decimal digit equivalent.
     */
    final static HashMap<String, Integer> upc_b_w = new HashMap<>();


    /**
     * Sets up the w-b-w-b and b-w-b-w pattern HashMaps
     */
    private static void setup()
    {
        upc_w_b.put("0001101", 0); upc_w_b.put("0011001", 1); upc_w_b.put("0010011", 2); upc_w_b.put("0111101", 3); upc_w_b.put("0100011", 4);
        upc_w_b.put("0110001", 5); upc_w_b.put("0101111", 6); upc_w_b.put("0111011", 7); upc_w_b.put("0110111", 8); upc_w_b.put("0001011", 9);

        upc_b_w.put("1110010", 0); upc_b_w.put("1100110", 1); upc_b_w.put("1101100", 2); upc_b_w.put("1000010", 3); upc_b_w.put("1011100", 4);
        upc_b_w.put("1001110", 5); upc_b_w.put("1010000", 6); upc_b_w.put("1000100", 7); upc_b_w.put("1001000", 8); upc_b_w.put("1110100", 9);

    }

    /**
     * Decodes the UPC-A code to its digit equivalent.
     * @param input String of UPC-A code to decode
     * @return returns the decoded String.
     */
    public static String decode(String input)
    {
        setup();
        StringBuilder returnStr = new StringBuilder();

        //sliding window
        int i;
        for(i=3; i<=38; i+=7)
        {
          returnStr.append(upc_w_b.get(input.substring(i, i+7)));
        }
        for (i=50; i<=78; i+=7)
        {
            returnStr.append(upc_b_w.get(input.substring(i, i+7)));
        }


        int checkDigit = upc_b_w.get(input.substring(i, i+7));
        return "Check digit: " + checkDigit + "" +
                "\nProduct code: " + returnStr;
    }

    /**
     * Validates the UPC-A String given by the user
     * @return Validated String input
     */
    public static String inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;


        String returnStr = "";

        do {
            try {
                returnStr = sc.nextLine();
                if(returnStr.length()!=95)
                {
                    throw new Exception("Invalid UPC-A Barcode : not correct amount of digits");
                }

                for(int i=0; i<returnStr.length(); i++)
                {
                    if(!Character.isDigit(returnStr.charAt(i)) && !(returnStr.charAt(i)== '1' || returnStr.charAt(i) == '0'))
                    {
                        throw new Exception("Invalid UPC-A Barcode : at least one character isn't a digit of 1 or 0");
                    }
                }
                valid = true;

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while(!valid);
        return returnStr;
    }



}
