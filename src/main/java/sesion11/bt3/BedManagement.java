package sesion11.bt3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BedManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/sesion11";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void updateBedStatus(String bedCode) {
        String sql = "UPDATE beds SET bed_status = 'Đang sử dụng' WHERE bed_code = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bedCode);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thành công: Đã cập nhật trạng thái giường '" + bedCode + "' thành 'Đang sử dụng'.");
            } else {
                System.out.println("Lỗi: Mã giường '" + bedCode + "' không tồn tại trong hệ thống. Vui lòng kiểm tra lại!");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống khi cập nhật cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test cập nhật Bed_001 ---");
        updateBedStatus("Bed_001");

        System.out.println("\n--- Test cập nhật Bed_999 ---");
        updateBedStatus("Bed_999");
    }
}
