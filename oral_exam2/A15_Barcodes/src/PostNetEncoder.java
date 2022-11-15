import java.util.HashMap;

public abstract class PostNetEncoder {

    final HashMap<Integer, String>  binary_equiv = new HashMap<>();

    private void setup()
    {
        binary_equiv.put(0, "11000"); binary_equiv.put(1, "00011"); binary_equiv.put(2, "00101");
        binary_equiv.put(3, "00110"); binary_equiv.put(4, "01001"); binary_equiv.put(5, "01010");
        binary_equiv.put(6, "01100"); binary_equiv.put(7, "10001"); binary_equiv.put(8, "10010");
        binary_equiv.put(9, "10100");


    }
    private int checkSum(int start) {return 10- (start % 10);}
    private String returnStringCreator (int [] nums)
    {
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("1 ");
        for(int num: nums)
        {
            returnStr.append(binary_equiv.get(num)).append(" ");
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
    public String encode(String zip)
    {
        setup();
        //First extract numbers from string
        int beforeCheckSum = 0;
        String [] string_nums = zip.split("\\d+");
        int [] integer_nums = new int [15];
        int position = 0;
        //Because there will only be a max of three strings. Runtime should be closer to O(n)
        for (String num : string_nums) {
            for (int j = 0; j < num.length(); j++) {
                beforeCheckSum += Character.getNumericValue(num.charAt(j));
                integer_nums[position] = Character.getNumericValue(num.charAt(j));
                position++;
            }
        }
        //Second, compute the checksum
        integer_nums[position] = checkSum(beforeCheckSum);

        //Third, return string of closing full height frame bars, and all binary of the numbers
        //also have a separate line with purely binary
        return returnStringCreator(integer_nums);
    }

}
