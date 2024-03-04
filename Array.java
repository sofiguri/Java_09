package Java_06;

import java.util.Scanner;

public class ArrayPI {
    public int[][] matrix1;
    public int[][] matrix2;

    public ArrayPI() {
        System.out.println("Введите количество строк первой матрицы:");
        Scanner scanner = new Scanner(System.in);
        int rows1 = scanner.nextInt();
        System.out.println("Введите количество столбцов первой матрици");
        int cols1 = scanner.nextInt();
        System.out.println("Введите количество строк втоорой матрицы");
        int rows2 = scanner.nextInt();
        System.out.println("Введите количество столбцов второй матрицы:");
        int cols2 = scanner.nextInt();

        if (rows1 == 0 || rows2 == 0 || cols2 == 0 || cols1 == 0){
            System.out.println("Такие матрицы невозможно считать");
            return;
        }

        matrix1 = new int[rows1][cols1];
        matrix2 = new int[rows2][cols2];

        System.out.println("Введите элементы первой матрицы:");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                matrix1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Введите элементы второй матрицы:");
        for (int i = 0; i < cols1; i++) {
            for (int j = 0; j < cols2; j++) {
                matrix2[i][j] = scanner.nextInt();
            }
        }
    }
}