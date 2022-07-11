package Matrix;

import java.io.Serializable;

public class Matrix implements Serializable {

    private float[][] imageR;
    private float[][] imageG;
    private float[][] imageB;
    private int size;
    private int fixedPoints;

    public Matrix(int size, int fixedPoints) {
        this.size = size;
        this.fixedPoints = fixedPoints;
        imageR = new float[this.size][this.size];
        imageG = new float[this.size][this.size];
        imageB = new float[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if ( (i==0) || (i==(size-1)) || (j==0) || (j==(size-1)) ) {
                    imageR[i][j] = 127.0f;
                    imageG[i][j] = 127.0f;
                    imageB[i][j] = 127.0f;
                } else {
                    imageR[i][j] = 0.0f;
                    imageG[i][j] = 0.0f;
                    imageB[i][j] = 0.0f;
                }
            }
        }
    }

    public void setImageR(int posX, int posY, float value) {
        imageR[posX][posY] = value;
    }

    public void setImageG(int posX, int posY, float value) {
        imageG[posX][posY] = value;
    }

    public void setImageB(int posX, int posY, float value) {
        imageB[posX][posY] = value;
    }

    public float getImageR(int posX, int posY) {
        return imageR[posX][posY];
    }

    public float getImageG(int posX, int posY) {
        return imageG[posX][posY];
    }

    public float getImageB(int posX, int posY) {
        return imageB[posX][posY];
    }

    public int getSize() {
        return size;
    }
}
