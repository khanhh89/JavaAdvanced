package sesion11.bt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    // phần 1:
    // khi dùng if chương trình sẽ ktra 1 lần duy nhất xem kết quả có dữ liệu hay k.
    // Nếu có nó di chuyển cn trỏ đến bản ghi đầu tiên và in ra, sau đó kết thúc
    // if chỉ xử lý được 1 bản ghi , muốn in toàn bộ phải dùng while
    // phần 2:
    public class PharmacyCatalogue {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/Hospital_DB";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "123456";
        private static final String DB_NAME = null;

        public static void main(String[] args) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                stmt = (PreparedStatement) conn.createStatement();

                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                System.out.println("Database đã sẵn sàng!");

                conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
                stmt = (PreparedStatement) conn.createStatement();

                // Tạo bảng Medicines nếu chưa có
                String createTable = "CREATE TABLE IF NOT EXISTS Medicines (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "quantity INT NOT NULL)";
                stmt.executeUpdate(createTable);
                System.out.println("Bảng Medicines đã sẵn sàng!");

                // Chèn dữ liệu mẫu
                stmt.executeUpdate("INSERT INTO Medicines (name, quantity) VALUES ('Paracetamol', 100)");
                stmt.executeUpdate("INSERT INTO Medicines (name, quantity) VALUES ('Amoxicillin', 50)");
                stmt.executeUpdate("INSERT INTO Medicines (name, quantity) VALUES ('Vitamin C', 200)");
                System.out.println("Đã thêm dữ liệu mẫu!");
                rs = stmt.executeQuery("SELECT name, quantity FROM Medicines");
                System.out.println("=== Danh mục thuốc trong kho ===");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    System.out.println("Tên thuốc: " + name + " | Số lượng tồn: " + quantity);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (stmt != null)
                        stmt.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
