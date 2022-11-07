import java.util.Random;

public abstract class Encryptor {

     final static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    public static int [] generateNValues(int msgLength)
    {

        Random rand = new Random();
        int [] key = new int[msgLength];
        int key_pos = 0;
        int randomNum;

        for(int i=0; i<msgLength; i++)
        {
            randomNum = rand.nextInt(26);
            key[key_pos] = randomNum;
        }
        return key;

    }
    public static String cypheredMessage(String message, int [] n_values)
    {
        char [] letters = message.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        int key_pos = 0;
        int character_index;
        for(char c: letters)
        {
            character_index = asciiBinarySearch(c, 25, 0);

            if(character_index == -1)
            {
                returnStr.append(' ');
            }else {
                // 1. Location in alphabet array  2. Value to be added to corresponding char 3. Length of alphabet array
                character_index = (character_index + n_values[key_pos]) % alphabet.length;
                returnStr.append(alphabet[character_index]);
            }
            key_pos++;

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
            else if(c > alphabet[middle])

            {
                return asciiBinarySearch(c, higher, middle + 1);
            }
            else {
                return asciiBinarySearch(c, middle - 1, lower);
            }
        }
    }

}
