import FileManager.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        for (int l = 0; l < 10; l++) {
            File file = new File("img.txt");
            DataManager manager = new DataManager(file);

            long start = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                for (int j = 1; j < manager.getMatrixSize() - 1; j++) {
                    for (int k = 1; k < manager.getMatrixSize() - 1; k++) {
                        int value = (manager.getMatrixRPoint(j, k) + manager.getMatrixRPoint(j - 1, k) + manager.getMatrixRPoint(j + 1, k) + manager.getMatrixRPoint(j, k - 1) + manager.getMatrixRPoint(j, k + 1)) / 5;
                        manager.setMatrixRPoint(j, k, value);
                        value = (manager.getMatrixGPoint(j, k) + manager.getMatrixGPoint(j - 1, k) + manager.getMatrixGPoint(j + 1, k) + manager.getMatrixGPoint(j, k - 1) + manager.getMatrixGPoint(j, k + 1)) / 5;
                        manager.setMatrixGPoint(j, k, value);
                        value = (manager.getMatrixBPoint(j, k) + manager.getMatrixBPoint(j - 1, k) + manager.getMatrixBPoint(j + 1, k) + manager.getMatrixBPoint(j, k - 1) + manager.getMatrixBPoint(j, k + 1)) / 5;
                        manager.setMatrixBPoint(j, k, value);
                    }
                }
                manager.readData(file);
            }
            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            manager.save(new File("output.txt"));
            System.out.print("Time elapsed in nanoseconds:");
            System.out.println(timeElapsed);
        }
    }
}