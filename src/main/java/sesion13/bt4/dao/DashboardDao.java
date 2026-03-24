package sesion13.bt4.dao;

import sesion13.bt4.model.BenhNhanDTO;
import sesion13.bt4.model.DichVu;
import sesion13.bt4.util.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardDao {

    public List<BenhNhanDTO> getDashboardData() {
        Map<String, BenhNhanDTO> patientMap = new LinkedHashMap<>();

        String sql = "SELECT b.ma_benh_nhan, b.ten_benh_nhan, d.ma_dich_vu, d.ten_dich_vu " +
                     "FROM benh_nhan b " +
                     "LEFT JOIN dich_vu_su_dung d ON b.ma_benh_nhan = d.ma_benh_nhan";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String maBN = rs.getString("ma_benh_nhan");
                String tenBN = rs.getString("ten_benh_nhan");

                BenhNhanDTO patient = patientMap.computeIfAbsent(maBN, key -> new BenhNhanDTO(key, tenBN));

                String maDV = rs.getString("ma_dich_vu");
                if (maDV != null) {
                    String tenDV = rs.getString("ten_dich_vu");
                    patient.addDichVu(new DichVu(maDV, tenDV));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy xuất dữ liệu Dashboard: " + e.getMessage());
        }

        return new ArrayList<>(patientMap.values());
    }
}