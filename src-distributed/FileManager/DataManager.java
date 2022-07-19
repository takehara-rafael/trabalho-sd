package FileManager;

import Matrix.Matrix;

import java.io.*;
import java.util.Scanner;

public class DataManager {

    public DataManager(File fileMatrix, Matrix matrix) throws IOException {
        try {
            Scanner scanner = new Scanner(fileMatrix);
            int size = scanner.nextInt();
            size = size+2;
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

    public void readData(File fileMatrix, Matrix matrix) throws IOException {
        try {
            Scanner scanner = new Scanner(fileMatrix);
            scanner.nextInt();
            scanner.nextInt();
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

    public void save(File fileOutput, Matrix matrix) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(fileOutput);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 1; i < matrix.getSize()+1; i++) {
                for (int j = 1; j < matrix.getSize()+1; j++){
                    String content = "< " + matrix.getImageR(i, j) + ", " + matrix.getImageG(i, j) + ", " + matrix.getImageB(i, j) + " > ";
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
