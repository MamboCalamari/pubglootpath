import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Main {

    public static void main(String args[])
    {
        File file = new File("D:\\Code\\pubglootpath\\myltaTest.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(scanner.nextLine());
        scanner.close();
    }

}
