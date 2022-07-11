package FileManager;

import Matrix.Matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                matrix.setImageR(posX, posY, scanner.nextFloat());
                matrix.setImageG(posX, posY, scanner.nextFloat());
                matrix.setImageB(posX, posY, scanner.nextFloat());
            }
            scanner.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void save(File fileOutput) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(fileOutput);
            for (int i = matrix.getSize(); i < matrix.getSize(); i++) {
                for (int j = matrix.getSize(); j < matrix.getSize(); j++){
                    myWriter.write("<");
                    myWriter.write(String.format("%.2f", matrix.getImageR(i, j)));
                    myWriter.write(", ");
                    myWriter.write(String.format("%.2f", matrix.getImageG(i, j)));
                    myWriter.write(", ");
                    myWriter.write(String.format("%.2f", matrix.getImageB(i, j)));
                    myWriter.write("> ");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void print() {
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                System.out.print(String.format("%.2f", matrix.getImageR(i, j)));
                System.out.print(" ");
                System.out.print(String.format("%.2f", matrix.getImageG(i, j)));
                System.out.print(" ");
                System.out.print(String.format("%.2f", matrix.getImageB(i, j)));
                System.out.print("\n");
            }
        }
    }


}
