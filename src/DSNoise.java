import java.util.Random;

public class DSNoise {
    public static int[][] generate(int seed, int xSize, int ySize) {
        xSize += 4;
        ySize += 4;

        int[][] result = new int[ySize][xSize];

        Random random = new Random();
        random.setSeed(seed);

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (i % 4 == 0 && j % 4 == 0) {
                    result[i][j] = random.nextInt(256);
                }
            }
        }

        for (int i = 2; i < ySize - 2; i++) {
            for (int j = 2; j < xSize - 2; j++) {
                if (i % 4 == 2 && j % 4 == 2) {
                    result[i][j] = (result[i - 2][j - 2] + result[i - 2][j + 2] + result[i + 2][j - 2] + result[i + 2][j + 2]) / 4;
                }
            }
        }

        for (int i = 2; i < ySize - 2; i++) {
            for (int j = 2; j < xSize - 2; j++) {
                if (i % 4 == 0 && j % 4 == 2) {
                    result[i][j] = (result[i + 2][j] + result[i][j - 2] + result[i][j + 2]) / 3;
                } else if (i % 4 == 2 && j % 4 == 0) {
                    result[i][j] = (result[i - 2][j] + result[i + 2][j] + result[i][j + 2]) / 3;
                }
            }
        }

        for (int i = 2; i < ySize - 2; i++) {
            for (int j = 2; j < xSize - 2; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    result[i][j] = (result[i - 1][j - 1] + result[i - 1][j + 1] + result[i + 1][j - 1] + result[i + 1][j + 1]) / 4;
                }
            }
        }

        for (int i = 2; i < ySize - 2; i++) {
            for (int j = 2; j < xSize - 2; j++) {
                if (i % 2 == 0 && j % 2 == 1) {
                    result[i][j] = (result[i + 1][j] + result[i][j - 1] + result[i][j + 1]) / 3;
                } else if (i % 2 == 1 && j % 2 == 0) {
                    result[i][j] = (result[i - 1][j] + result[i + 1][j] + result[i][j + 1]) / 3;
                }
            }
        }

        int[][] finalResult = new int[ySize - 4][xSize - 4];

        for(int i=0; i<ySize - 4; i++) {
            for(int j=0; j<xSize - 4; j++) {
                finalResult[i][j] = result[i + 2][j + 2];
            }
        }

        return finalResult;
    }
}
