package sesion13.bt3.dao;

import sesion13.bt3.util.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DischargeDao {

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) throws Exception {
        String sqlSelect = "SELECT so_du_tam_ung, ma_giuong FROM benh_nhan WHERE id = ?";
        String sqlUpdateTien = "UPDATE benh_nhan SET so_du_tam_ung = so_du_tam_ung - ? WHERE id = ?";
        String sqlUpdateGiuong = "UPDATE giuong_benh SET trang_thai = 'Trống' WHERE ma_giuong = ?";
        String sqlUpdateTrangThai = "UPDATE benh_nhan SET trang_thai = 'Đã xuất viện' WHERE id = ?";

        Connection conn = null;

        try {
            conn = DatabaseHelper.getConnection();
            
            conn.setAutoCommit(false); 

            double soDu = 0;
            String maGiuong = null;
            try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
                pstmtSelect.setInt(1, maBenhNhan);
                try (ResultSet rs = pstmtSelect.executeQuery()) {
                    if (rs.next()) {
                        soDu = rs.getDouble("so_du_tam_ung");
                        maGiuong = rs.getString("ma_giuong");
                    } else {
                        throw new SQLException("Không tìm thấy bệnh nhân với ID: " + maBenhNhan);
                    }
                }
            }

            if (soDu < tienVienPhi) {
                throw new IllegalArgumentException("Số dư tạm ứng (" + soDu + ") không đủ để thanh toán viện phí (" + tienVienPhi + "). Vui lòng nộp thêm!");
            }

            try (PreparedStatement pstmtThuTien = conn.prepareStatement(sqlUpdateTien)) {
                pstmtThuTien.setDouble(1, tienVienPhi);
                pstmtThuTien.setInt(2, maBenhNhan);
                int rowsAffected = pstmtThuTien.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Lỗi khi trừ tiền: Không có dòng dữ liệu nào được cập nhật.");
                }
            }

            try (PreparedStatement pstmtGiuong = conn.prepareStatement(sqlUpdateGiuong)) {
                pstmtGiuong.setString(1, maGiuong);
                int rowsAffected = pstmtGiuong.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Lỗi giải phóng giường: Không tìm thấy mã giường '" + maGiuong + "' trong hệ thống để giải phóng.");
                }
            }

            try (PreparedStatement pstmtTrangThai = conn.prepareStatement(sqlUpdateTrangThai)) {
                pstmtTrangThai.setInt(1, maBenhNhan);
                int rowsAffected = pstmtTrangThai.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Lỗi cập nhật trạng thái bệnh nhân: Không có dòng dữ liệu nào bị ảnh hưởng.");
                }
            }

            conn.commit();
            System.out.println(">> THÀNH CÔNG: Giao dịch hoàn tất! Bệnh nhân " + maBenhNhan + " đã được xuất viện.");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println(">> ĐÃ ROLLBACK: Toàn bộ quá trình vừa rồi đã được hoàn tác lại như cũ.");
                } catch (SQLException ex) {
                    e.addSuppressed(ex);
                }
            }
            throw e; 
            
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
