import java.util.Scanner;

public class DRIVERPostNetEncoder {

    public static void main (String [] args)
    {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Zip code to encode");
        System.out.println(PostNetEncoder.encode(sc.nextLine()));
    }


//    private static String inputValidation()
//    {
//        final Scanner sc = new Scanner(System.in);
//        boolean valid = false;
//
//        String returnStr = "";
//
//        do {
//            try {
//
//                if(sc.hasNextInt())
//                {
//                    returnStr = sc.nextLine();
//                    System.out.println(returnStr);
//                    valid = true;
//                }else
//                {
//                    throw new Exception("Invalid Zip Code");
//                }
//            }catch (Exception e)
//            {
//                System.out.println(e.getMessage());
//                sc.nextLine();
//            }
//        }while(!valid);
//        return returnStr;
//
//    }
}


