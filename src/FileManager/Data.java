package FileManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    private int[][] imageR;
    private int[][] imageG;
    private int[][] imageB;
    private int size;
    private int fixedPoints;

    public Data(File fileMatrix) throws IOException {
        try {
            Scanner scanner = new Scanner(fileMatrix);
            size = scanner.nextInt();
            fixedPoints = scanner.nextInt();
            while (scanner.hasNextLine()) {
                int posX, posY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                imageR[posX][posY] = scanner.nextInt();
                imageG[posX][posY] = scanner.nextInt();
                imageB[posX][posY] = scanner.nextInt();
            }
            scanner.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void save(File fileOutput) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(fileOutput);
            for (int i = size; i < size; i++) {
                for (int j = size; j < size; j++){
                    myWriter.write("<");
                    myWriter.write(imageR[i][j]);
                    myWriter.write(", ");
                    myWriter.write(imageG[i][j]);
                    myWriter.write(", ");
                    myWriter.write(imageB[i][j]);
                    myWriter.write(">");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            throw e;
        }
    }


}
