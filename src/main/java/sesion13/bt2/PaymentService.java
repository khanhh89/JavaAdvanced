package sesion13.bt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void thanhToanVienPhi() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
            con.setAutoCommit(false); 

            String sql1 = "UPDATE Patient_Wallet SET balance = balance - 500 WHERE patient_id = 1";
            try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
                pstmt1.executeUpdate();
                System.out.println("Đã trừ tiền trong ví bệnh nhân.");
            }

            String sql2 = "UPDATE Invoices_SaiTen SET status = 'Đã thanh toán' WHERE patient_id = 1";
            try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
                pstmt2.executeUpdate();
                System.out.println("Đã cập nhật trạng thái hóa đơn.");
            }

            con.commit();
            System.out.println("Giao dịch thanh toán thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống: " + e.getMessage());
            
            if (con != null) {
                try {
                    System.out.println("Đang tiến hành ROLLBACK để hoàn tác trừ tiền...");
                    con.rollback();
                    System.out.println("ROLLBACK THÀNH CÔNG: Tiền đã được trả lại về trạng thái ban đầu!");
                } catch (SQLException ex) {
                    System.err.println("Lỗi nghiêm trọng khi thực hiện Rollback: " + ex.getMessage());
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
        System.out.println("Bắt đầu thử nghiệm thanh toán...");
        thanhToanVienPhi();
    }
}
