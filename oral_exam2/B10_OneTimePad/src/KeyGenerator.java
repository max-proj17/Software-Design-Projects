import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class KeyGenerator {

    public static void generateNValues(int msgLength) throws IOException {
        File nValues = new File("oral_exam2/B10_OneTimePad/Files/n_values.txt");
        Random rand = new Random();
        int randomNum;

        if(nValues.exists())
        {
            System.out.println("File already exists");

        }else {
            if(nValues.createNewFile())
            {
                System.out.println("Creating file...");
            }
            else {
                System.out.println("Failed to make file");
            }
        }
        FileWriter fileWriter = new FileWriter(nValues, false);
        randomNum = rand.nextInt(msgLength)+1;
        fileWriter.write(randomNum + "\n");




        for(int i=0; i<msgLength; i++)
        {
            if(i == msgLength - 1)
            {
                randomNum = rand.nextInt(26);
                fileWriter.write(randomNum + "\n");
            }
            else {
                randomNum = rand.nextInt(26);
                fileWriter.write(randomNum + ",");
            }

        }
        fileWriter.close();

    }
    public static int inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;
        int values_length = 0;
        int tmp;
        do {
            try {
                if (sc.hasNextInt()) {
                    tmp = sc.nextInt();
                    if(tmp > 10)
                    {
                        valid = true;
                        values_length = tmp;
                    }else {
                        throw new Exception("Invalid Input");
                    }
                } else {
                    throw new Exception("Invalid Input");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Input");
                sc.nextLine();

            }
        }while(!valid);
        return values_length;
    }
    public static void main(String [] args) throws IOException {
        System.out.println("How many N values would you like to generate?\n" +
                "Number must be greater than 10");
        generateNValues(inputValidation());

    }
}
