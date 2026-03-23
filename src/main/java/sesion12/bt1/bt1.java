package sesion12.bt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bt1 {
    // phần 1: 
    // khi sử dụng Statement thông thường, câu lệnh SQL và dữ liệu nhập vào bị trộn thành chuỗi duy nhất. Kẻ tấn công có thể nhập các kí tự đặc biệt ' OR ' '1' = '1' để thay đổi cấu trúc câu lệnh
    // PreparedStatement hoạt động theo cơ chế tách biệt hoàn toàn giữa lệnh command và dữ liệu Data
    // +Gửi cấu trúc dữ liệu SQL lên DataBase trước với các dấu ?
    //+Database hiểu: Đây là câu truy vấn tìm bác sĩ, có 2 giá  trị cụ thể được lấp vào sau
    // Cơ chế "Pre-compiled"
    // +Xác định cấu trúc cố định
    // + xử lý ký tự đặc biệt
    // Phần 2:

public static void main(String[] args) {
        // Giả sử các thông tin kết nối Database
        String url = "jdbc:mysql://localhost:3306/sesion12";
        String user = "root";
        String pass = "123456";
        String inputDoctorCode = "DOC001";
        String inputPassword = "' OR '1'='1";
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "SELECT * FROM doctors WHERE doctor_code = ? AND password = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, inputDoctorCode);
                pstmt.setString(2, inputPassword);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Đăng nhập thành công! Chào bác sĩ: " + rs.getString("name"));
                } else {
                    System.out.println("Lỗi: Mã số hoặc mật khẩu không chính xác.");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
