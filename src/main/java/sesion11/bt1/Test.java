package sesion11.bt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    // Phần 1: Tại sao không đóng kết nối lại nguy hiểm?
    // - Gây rò rỉ tài nguyên (Connection Leak) làm cạn kiệt RAM và cổng mạng.
    // - Vượt quá giới hạn kết nối tối đa của MySQL, khiến database từ chối phục vụ và làm "treo" hệ thống.
    // - Kết nối để lâu bị MySQL ngắt ngầm, khi phần mềm gọi lại sẽ bị lỗi Communications link failure.
    // - Hệ thống y tế cần chạy 24/7, việc treo hệ thống làm gián đoạn cấp cứu, ảnh hưởng tính mạng bệnh nhân.

    // Phần 2: Thực thi
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sesion11";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void getPatientRecord(int patientId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String sql = "SELECT * FROM patient WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID Bệnh nhân: " + rs.getInt("id"));
                System.out.println("Họ tên: " + rs.getString("full_name"));
                System.out.println("Chẩn đoán: " + rs.getString("diagnosis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Test db = new Test();
        System.out.println("Đang truy xuất hồ sơ bệnh nhân...");
        db.getPatientRecord(1);
    }
}