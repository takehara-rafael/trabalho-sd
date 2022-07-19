import FileManager.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's the file name?");
        String fileName = scanner.nextLine();
         */
        String fileName = "test.txt";
        FileManager.defineFile(fileName);
        FileManager.loadData();
        FileManager.saveData();
    }
}