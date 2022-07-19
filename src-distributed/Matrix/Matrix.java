package Matrix;

import java.io.Serializable;

public class Matrix implements Serializable {

    private int[][] imageR;
    private int[][] imageG;
    private int[][] imageB;
    private int size;
    private int fixedPoints;

    public Matrix(int size, int fixedPoints) {
        this.size = size;
        this.fixedPoints = fixedPoints;
        imageR = new int[this.size][this.size];
        imageG = new int[this.size][this.size];
        imageB = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if ( (i==0) || (i==(size-1)) || (j==0) || (j==(size-1)) ) {
                    imageR[i][j] = 127;
                    imageG[i][j] = 127;
                    imageB[i][j] = 127;
                } else {
                    imageR[i][j] = 0;
                    imageG[i][j] = 0;
                    imageB[i][j] = 0;
                }
            }
        }
    }

    public void setImageR(int posX, int posY, int value) {
        imageR[posX][posY] = value;
    }

    public void setImageG(int posX, int posY, int value) {
        imageG[posX][posY] = value;
    }

    public void setImageB(int posX, int posY, int value) {
        imageB[posX][posY] = value;
    }

    public int getImageR(int posX, int posY) {
        return imageR[posX][posY];
    }

    public int getImageG(int posX, int posY) {
        return imageG[posX][posY];
    }

    public int getImageB(int posX, int posY) {
        return imageB[posX][posY];
    }

    public int[][] getMatrixR(int start, int end, int lines) {
        int[][] matrix = new int[lines][size];
        for (int i = start; i < end; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i-start][j] = imageR[i][j];
            }
        }
        return matrix;
    }

    public int[][] getMatrixG(int start, int end, int lines) {
        int[][] matrix = new int[lines][size];
        for (int i = start; i < end; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i-start][j] = imageG[i][j];
            }
        }
        return matrix;
    }

    public int[][] getMatrixB(int start, int end, int lines) {
        int[][] matrix = new int[lines][size];
        for (int i = start; i < end; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i-start][j] = imageB[i][j];
            }
        }
        return matrix;
    }

    public int getSize() {
        return size-2;
    }
}
