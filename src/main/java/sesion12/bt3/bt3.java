package sesion12.bt3;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bt3 {
    //phần 1:
    // Trong JDBC, khi làm việc với Stored Procedure có tham số đầu ra (OUT), trình điều khiển (Driver) cần biết trước kiểu dữ liệu mà cơ sở dữ liệu sẽ trả về để cấp phát bộ nhớ và chuẩn bị bộ chuyển đổi kiểu (Type Mapper) phù hợp.
    // Xác định kiểu dữ liệu: registerOutParameter() thông báo cho JDBC rằng dấu hỏi chấm (?) tại vị trí đó không phải là dữ liệu đầu vào mà là nơi để nhận giá trị trả về.
    // Tránh lỗi Index: Nếu không đăng ký, khi bạn cố gắng lấy dữ liệu bằng các hàm get...(), Driver sẽ báo lỗi Column index out of range vì nó chưa được cấu hình để "hứng" giá trị tại vị trí đó
    // Nếu trong SQL tham số đầu ra được định nghĩa là DECIMAL hoặc NUMERIC, ta sử dụng java.sql.Types.DECIMAL và nhận kết quả bằng getBigDecimal().

    // phần 2:
    public static  void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sesion12";
        String user = "root";
        String pass = "123456";
        int inputSurgeryId = 501;
        String sql = "{ call GET_SURGERY_FEE(?, ?) }";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, inputSurgeryId);
            cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);
            cstmt.execute();
            BigDecimal fee = cstmt.getBigDecimal(2);
            if (fee != null) {
                System.out.println("Mã phẫu thuật: " + inputSurgeryId);
                System.out.println("Tổng chi phí: " + fee + " VND");
            } else {
                System.out.println("Không tìm thấy dữ liệu chi phí.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
