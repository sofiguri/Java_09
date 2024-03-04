package Java_06;

import Java_sonya.ExcelExporter;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Matrix matrixOps = null;
    private static DBhandler dbHandler = new DBhandler();
    private  static ExcelExporter excelExporter = new ExcelExporter();

    private static int[][] readMatrixFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Введите элементы матрицы построчно:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }


    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("1. Вывести все таблицы из базы данных MySQL.");
            System.out.println("2. Создать таблицу в базе данных MySQL.");
            System.out.println("3. Ввести две матрицы с клавиатуры и каждую из них сохранить в MySQL с последующим выводом в консоль.");
            System.out.println("4. Перемножить, сложить, вычесть, возвести в степень матрицы, а также сохранить результаты в MySQL и вывести в консоль.");
            System.out.println("5. Сохранить результаты из MySQL в Excel и вывести их в консоль.");
            System.out.println("6. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    dbHandler.showTables();
                    break;
                case 2:
                    System.out.print("Введите название таблицы для создания: ");
                    String tableName = scanner.next();
                    dbHandler.createTable(tableName);
                    break;
                case 3:
                    matrixOps = new Matrix();
                    System.out.println("Введите первую матрицу:");
                    matrixOps.setMatrix1(readMatrixFromConsole());
                    System.out.println("Введите вторую матрицу:");
                    matrixOps.setMatrix2(readMatrixFromConsole());
                    dbHandler.saveMatrix("matrix1", matrixOps.getMatrix1());
                    dbHandler.saveMatrix("matrix2", matrixOps.getMatrix2());
                    dbHandler.printMatrixFromDB("matrix1");
                    dbHandler.printMatrixFromDB("matrix2");
                    break;
                case 4:
                    if (matrixOps != null) {
                        matrixOps.multiplyMatrices();
                        matrixOps.addMatrices();
                        matrixOps.subtractMatrices();
                        matrixOps.powMatrix();

                    } else {
                        System.out.println("Сначала инициализируйте матрицы (выберите пункт 3).");
                    }
                    break;
                case 5:
                    excelExporter.export();
                    break;
                case 6:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие из списка.");
                    break;
            }
        }
    }
}