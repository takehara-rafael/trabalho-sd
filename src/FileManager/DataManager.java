package FileManager;

import Matrix.Matrix;

import java.io.*;
import java.util.Scanner;

public class DataManager {
    private Matrix matrix;

    public DataManager(File fileMatrix) throws IOException {
        try {
            Scanner scanner = new Scanner(fileMatrix);
            int size = scanner.nextInt();
            int fixedPoints = scanner.nextInt();
            matrix = new Matrix(size, fixedPoints);
            while (scanner.hasNextLine()) {
                int posX, posY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                matrix.setImageR(posX, posY, scanner.nextInt());
                matrix.setImageG(posX, posY, scanner.nextInt());
                matrix.setImageB(posX, posY, scanner.nextInt());
            }
            scanner.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void save(File fileOutput) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(fileOutput);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < matrix.getSize(); i++) {
                for (int j = 0; j < matrix.getSize(); j++){
                    String content = "< " + matrix.getImageR(i, j) + ", " + matrix.getImageG(i, j) + ", " + matrix.getImageB(i, j) + "> ";
                    printWriter.print(content);
                }
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (IOException e) {
            throw e;
        }
    }



}
