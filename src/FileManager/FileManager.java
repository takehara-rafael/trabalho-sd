package FileManager;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {
    private static Data dataBase;
    private static File fileMatrix;
    private static File fileOutput;


    public static void defineFile(String filePathM, String filePathO) {
        fileMatrix = new File(filePathM);
        fileOutput = new File(filePathO);
    }

    public static void loadData() throws IOException {
        try {
            dataBase = new Data(fileMatrix);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void saveData() throws IOException {
        dataBase.save(fileOutput);
    }



}
