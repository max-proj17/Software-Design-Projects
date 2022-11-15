import java.util.Scanner;

public class DRIVERPostNetDecoder {

    public static void main (String [] args)
    {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter a binary representation of a zip code");
        String tmp = sc.nextLine();
        System.out.println(tmp);
        System.out.println(PostNetDecoder.decode(tmp));
    }
}
