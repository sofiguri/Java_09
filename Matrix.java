package Java_06;

import java.util.Scanner;

public class Matrix {
    private int[][] matrix1;
    private int[][] matrix2;

    public Matrix() {
    }

    public int[][] getMatrix1() {
        return matrix1;
    }

    public int[][] getMatrix2() {
        return matrix2;
    }

    public void setMatrix1(int[][] matrix) {
        this.matrix1 = matrix;
    }

    public void setMatrix2(int[][] matrix) {
        this.matrix2 = matrix;
    }

    public void multiplyMatrices() {
        if (matrix1 == null || matrix2 == null) {
            System.out.println("Матрицы не инициализированы.");
            return;
        }
        if (matrix1[0].length != matrix2.length) {
            System.out.println("Умножение невозможно: количество столбцов первой матрицы и строк второй матрицы не совпадает.");
            return;
        }

        int rowsResult = matrix1.length;
        int colsResult = matrix2[0].length;
        int[][] resultMatrix = new int[rowsResult][colsResult];

        for (int i = 0; i < rowsResult; i++) {
            for (int j = 0; j < colsResult; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        System.out.println("Произведение матриц:");
        for (int[] row : resultMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public void addMatrices() {
        if (matrix1 == null || matrix2 == null) {
            System.out.println("Матрицы не инициализированы.");
            return;
        }
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            System.out.println("Сложение невозможно: размеры матриц не совпадают.");
            return;
        }

        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] resultMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        System.out.println("Сумма матриц:");
        for (int[] row : resultMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public void subtractMatrices() {
        if (matrix1 == null || matrix2 == null) {
            System.out.println("Матрицы не инициализированы.");
            return;
        }
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            System.out.println("Вычитание невозможно: размеры матриц не совпадают.");
            return;
        }

        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] resultMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        System.out.println("Разность матриц:");
        for (int[] row : resultMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public void powMatrix(int pow) {
        if (matrix1 == null) {
            System.out.println("Матрица не инициализирована.");
            return;
        }

        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] resultMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = matrix1[i][j];
            }
        }

        for (int k = 1; k < pow; k++) {
            int[][] tempMatrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int n = 0; n < cols; n++) {
                        tempMatrix[i][j] += matrix1[i][n] * resultMatrix[n][j];
                    }
                }
            }
            resultMatrix = tempMatrix;
        }

        System.out.println("Матрица, возведенная в степень " + pow + ":");
        for (int[] row : resultMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Метод для чтения матрицы из консоли
    public static int[][] readMatrixFromConsole(Scanner scanner) {
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }
}