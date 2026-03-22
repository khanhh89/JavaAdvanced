package sesion11.bt4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientSearch {

    private static final String URL = "jdbc:mysql://localhost:3306/sesion11";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    /*
     * PHẦN 1 - PHÂN TÍCH LỖI SQL INJECTION
     * Giả sử câu truy vấn gốc trong mã nguồn bị lỗi là:
     * String sql = "SELECT * FROM patients WHERE name = '" + inputName + "'";
     * Nếu người dùng (hoặc hacker) nhập vào inputName là: ' OR '1'='1
     * Khi thực hiện nối chuỗi, câu truy vấn sẽ trở thành:
     * SELECT * FROM patients WHERE name = '' OR '1'='1'
     * Tại sao mệnh đề WHERE lại luôn đúng?
     * Mệnh đề WHERE lúc này bao gồm 2 điều kiện được nối với nhau bằng toán tử OR:
     * 1. name = '' -> Thường là sai (False) vì ít có bệnh nhân nào tên rỗng.
     * 2. '1'='1' -> Điều kiện này luôn luôn đúng (True).
     * Theo logic Boolean: (False OR True) = True.
     * Do đó, mệnh đề WHERE được đánh giá là True đối với *TẤT CẢ* các dòng trong bảng `patients`.
     * Hậu quả là toàn bộ dữ liệu của tất cả bệnh nhân sẽ bị truy xuất và trả về, gây rò rỉ thông tin nghiêm trọng.
     */
    
    public static void searchPatient(String inputName) {
        String sanitizedInput = sanitizeInput(inputName);
        String sql = "SELECT * FROM patients WHERE name = '" + sanitizedInput + "'";
        System.out.println("Executing query: " + sql);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Tìm thấy: [ID: " + rs.getInt("id") + 
                                   ", Tên: " + rs.getString("name") + 
                                   ", Bệnh án: " + rs.getString("disease") + "]");
            }

            if (!found) {
                System.out.println("Không tìm thấy bệnh nhân nào khớp với từ khóa.");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
    }
    private static String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.replace("'", "")
                    .replace(";", "")
                    .replace("--", "");
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Bác sĩ tìm kiếm tên bình thường ---");
        searchPatient("Nguyen Van A");

        System.out.println("\n--- Test 2: Hacker chèn mã SQL Injection ---");
        searchPatient("' OR '1'='1");
    }
}
