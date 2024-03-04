package Java_06;

import java.sql.*;

public class DBhandler {
    private static final String URL = "jdbc:mysql://localhost:3306/Java_06";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void showTables() {
        String sql = "SHOW TABLES";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Список таблиц в базе данных:");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе списка таблиц: " + e.getMessage());
        }
    }

    public static void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY, " +
                "`matrix_row` INT NOT NULL, " +
                "`matrix_col` INT NOT NULL, " +
                "`value` INT NOT NULL)";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица " + tableName + " создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }


    public static void saveMatrix(String tableName, int[][] matrix) {
        String sql = "INSERT INTO `" + tableName + "` (`matrix_row`, `matrix_col`, `value`) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    statement.setInt(1, i);
                    statement.setInt(2, j);
                    statement.setInt(3, matrix[i][j]);
                    statement.addBatch();
                }
            }
            statement.executeBatch();
            conn.commit();
            System.out.println("Матрица сохранена в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    public static void printMatrixFromDB(String tableName) {
        String sql = "SELECT * FROM `" + tableName + "` ORDER BY `matrix_row`, `matrix_col`";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int row = rs.getInt("matrix_row");
                int col = rs.getInt("matrix_col");
                int value = rs.getInt("value");
                System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе матрицы из БД: " + e.getMessage());
        }
    }
}
