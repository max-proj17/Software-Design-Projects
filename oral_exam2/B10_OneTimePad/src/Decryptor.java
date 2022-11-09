public class Decryptor {

    final static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

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
    public static String decrypt(String message, int [] n_values)
    {

        char [] letters = message.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        int key_pos = 0;
        int character_index;
        for(char c: letters)
        {
            character_index = asciiBinarySearch(Character.toUpperCase(c), 25, 0);
            if(character_index == -1){
                System.out.print(" : " + n_values[key_pos]);
            }else {
                System.out.print(" " + alphabet[character_index] + " " + n_values[key_pos]);
            }

            if(character_index == -1)
            {
                returnStr.append(' ');
            }else {
                // 1. Location in alphabet array  2. Value to be added to corresponding char 3. Length of alphabet array
                //if a negative index
                if(character_index == 0)
                {
                    character_index = alphabet.length - n_values[key_pos];
                }
                else if(character_index - n_values[key_pos] < 0)
                {
                    character_index = -1 * (character_index - n_values[key_pos]);
                }else //if not a negative index
                {
                    character_index = character_index - n_values[key_pos];
                }
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
