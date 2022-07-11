package FileManager;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {
    private static DataManager dataManager;
    private static File fileMatrix;
    private static File fileOutput;


    public static void defineFile(String filePathM) {
        fileMatrix = new File(filePathM);
        fileOutput = new File("output.txt");
    }

    public static void loadData() throws IOException {
        try {
            dataManager = new DataManager(fileMatrix);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void saveData() throws IOException {
        dataManager.save(fileOutput);
    }

    public static void printMatrix() {
        dataManager.print();
    }


}
