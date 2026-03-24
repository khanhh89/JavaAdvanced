package sesion13.bt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionService {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void keDonThuoc() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            con.setAutoCommit(false);

            String sql1 = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE med_id = 'M001'";
            try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
                pstmt1.executeUpdate();
                System.out.println("Đã trừ 1 đơn vị thuốc trong kho.");
            }

            String sql2 = "INSERT INTO Prescription_History (patient_id, med_id) VALUES (1, 'M001')";
            try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
                pstmt2.executeUpdate();
                System.out.println("Đã lưu lịch sử cấp phát thuốc.");
            }

            con.commit();
            System.out.println("Giao dịch kê đơn hoàn tất thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống trong quá trình kê đơn: " + e.getMessage());
            
            if (con != null) {
                try {
                    System.out.println("Tiến hành khôi phục (Rollback) lại số lượng thuốc do giao dịch thất bại...");
                    con.rollback();
                } catch (SQLException ex) {
                    System.err.println("Lỗi khi Rollback: " + ex.getMessage());
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        keDonThuoc();
    }
}
