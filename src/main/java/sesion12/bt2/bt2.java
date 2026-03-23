package sesion12.bt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bt2 {
    // phần 1:
    // dùng Statement nối chuỗi, Java phải biến con số 37.5 thành một chuỗi văn bản (String)
    // Nếu máy tính hiểu theo kiểu Việt Nam, nó sẽ biến thành "37,5". Khi đưa vào câu lệnh SQL: UPDATE ... SET temperature = 37,5, SQL sẽ hiểu dấu phẩy là dấu ngắt giữa các cột, dẫn đến lỗi cú pháp.
    // PreparedStatement giải quyết việc này bằng cách:
    // Tách biệt dữ liệu và lệnh: Nó gửi câu lệnh SQL có chứa dấu hỏi chấm (?) lên Database trước
    // Truyền dữ liệu nhị phân: Khi bạn gọi setDouble(1, 37.5), giá trị này được truyền đi dưới dạng số nhị phân (binary)
    public static void main(String[] args){
        double currentTemp = 37.5;
        int currentHeartRate = 85;
        int patientId = 101;
        String sql = "UPDATE patient_vitals SET temperature = ?, heart_rate = ? WHERE patient_id = ?";
        Connection conn = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, currentTemp);
            pstmt.setInt(2, currentHeartRate);
            pstmt.setInt(3, patientId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật chỉ số sinh tồn thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân có ID: " + patientId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

