package sesion12.bt4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LabResultLoader {
    private static final String URL = "jdbc:mysql://localhost:3306/sesion12";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // PHẦN 1:

// 1. Hiện trạng lỗi: Khi sử dụng Statement và nối chuỗi bên trong vòng lặp 1.000 lần,
// mỗi câu lệnh SQL gửi đến Database Server sẽ là một chuỗi hoàn toàn khác biệt.
// Ví dụ:
// - INSERT INTO blood_tests (patient_id, result) VALUES (1, 'Result 1');
// - INSERT INTO blood_tests (patient_id, result) VALUES (2, 'Result 2');

// 2. Quá trình xử lý của Database: Khi nhận một câu SQL mới, hệ quản trị CSDL (như MySQL)
// phải thực hiện các bước sau:
// - Phân tích cú pháp (Parsing).
// - Kiểm tra quyền truy cập (Authorization).
// - Tối ưu hóa truy vấn (Optimization).
// - Lập kế hoạch thực thi (Execution Plan).

// 3. Sự lãng phí: Vì 1.000 câu lệnh có chuỗi khác nhau, CSDL không nhận ra chúng có cùng
// cấu trúc. Nó buộc phải thực hiện lại toàn bộ quá trình Parse & Lập Execution Plan
// 1.000 lần. Điều này gây lãng phí cực lớn tài nguyên CPU và bộ nhớ của Server,
// làm chậm đáng kể tốc độ nạp dữ liệu.

// 4. Giải pháp: Sử dụng PreparedStatement. Cấu trúc câu lệnh (có chứa dấu ?) được biên
// dịch (Compile) và lên kế hoạch thực thi 1 LẦN DUY NHẤT. Trong vòng lặp, chúng ta
// chỉ việc truyền giá trị tham số mới.

    public static void main(String[] args) {
        setupTable();

        System.out.println("Đang bắt đầu thử nghiệm chèn 1000 bản ghi...");

        long startTimeStatement = System.currentTimeMillis();
        insertUsingStatement();
        long endTimeStatement = System.currentTimeMillis();
        System.out.println("Thời gian chèn 1000 bản ghi bằng Statement: " + (endTimeStatement - startTimeStatement) + " ms");

        clearTable();
        long startTimePreparedStatement = System.currentTimeMillis();
        insertUsingPreparedStatement();
        long endTimePreparedStatement = System.currentTimeMillis();
        System.out.println("Thời gian chèn 1000 bản ghi bằng PreparedStatement: " + (endTimePreparedStatement - startTimePreparedStatement) + " ms");
    }
     // PHẦN 2: Giải pháp xử lý bằng PreparedStatement
    private static void insertUsingPreparedStatement() {
        String sql = "INSERT INTO blood_tests (patient_id, result) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 1; i <= 1000; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "Result " + i);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertUsingStatement() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            for (int i = 1; i <= 1000; i++) {
                String sql = "INSERT INTO blood_tests (patient_id, result) VALUES (" + i + ", 'Result " + i + "')";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void setupTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS blood_tests (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "patient_id INT, " +
                                "result VARCHAR(255))";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            stmt.execute("TRUNCATE TABLE blood_tests");
        } catch (SQLException e) {
            System.err.println("Vui lòng tạo database 'hospital_db' và cấu hình User/Password trước: " + e.getMessage());
        }
    }

    private static void clearTable() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("TRUNCATE TABLE blood_tests");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
