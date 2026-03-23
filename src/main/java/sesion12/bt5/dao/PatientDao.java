package sesion12.bt5.dao;

import sesion12.bt5.model.Patient;
import sesion12.bt5.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {

    // 1. Lấy danh sách bệnh nhân
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM inpatients";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("pathology"),
                        rs.getInt("admission_days")
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách bệnh nhân: " + e.getMessage());
        }
        return patients;
    }

    // 2. Tiếp nhận bệnh nhân mới (Chống SQL Injection bằng PreparedStatement)
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO inpatients (id, name, age, department, pathology, admission_days) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // PreparedStatement tự động handle các tên có chứa dấu nháy đơn như D'Arcy hay L'Oréal
            pstmt.setString(1, patient.getId());
            pstmt.setString(2, patient.getName());
            pstmt.setInt(3, patient.getAge());
            pstmt.setString(4, patient.getDepartment());
            pstmt.setString(5, patient.getPathology());
            pstmt.setInt(6, patient.getAdmissionDays());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi thêm bệnh nhân: " + e.getMessage());
            return false;
        }
    }

    // 3. Cập nhật bệnh án
    public boolean updatePathology(String id, String newPathology) {
        String sql = "UPDATE inpatients SET pathology = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, newPathology);
            pstmt.setString(2, id);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật bệnh án: " + e.getMessage());
            return false;
        }
    }

    // 4. Xuất viện & Tính phí (Gọi Stored Procedure)
    public double calculateDischargeFee(String patientId) {
        // Cú pháp gọi Stored Procedure: {CALL tên_procedure(?, ?)}
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
             
            // Truyền tham số IN (Tham số đầu vào)
            cstmt.setString(1, patientId);
            
            // Đăng ký kiểu dữ liệu cho tham số OUT (Tham số đầu ra)
            cstmt.registerOutParameter(2, Types.DECIMAL);
            
            // Thực thi Stored Procedure
            cstmt.execute();
            
            // Lấy kết quả từ tham số OUT
            return cstmt.getDouble(2);
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi Stored Procedure tính viện phí: " + e.getMessage());
            return -1;
        }
    }
}
