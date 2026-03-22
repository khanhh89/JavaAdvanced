package sesion11.bt5.dao;

import sesion11.bt5.model.Doctor;
import sesion11.bt5.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDao {
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách bác sĩ: " + e.getMessage());
        }
        return doctors;
    }

    public boolean addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO Doctors (id, name, specialty) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Sử dụng PreparedStatement để chống SQL Injection và truyền dữ liệu an toàn
            pstmt.setString(1, doctor.getId());
            pstmt.setString(2, doctor.getName());
            pstmt.setString(3, doctor.getSpecialty());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public Map<String, Integer> getStatisticsBySpecialty() {
        Map<String, Integer> stats = new HashMap<>();
        String sql = "SELECT specialty, COUNT(*) as count FROM Doctors GROUP BY specialty";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                stats.put(rs.getString("specialty"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thống kê: " + e.getMessage());
        }
        return stats;
    }
}
