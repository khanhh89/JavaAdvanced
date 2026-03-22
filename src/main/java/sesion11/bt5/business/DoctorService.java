package sesion11.bt5.business;

import sesion11.bt5.dao.DoctorDao;
import sesion11.bt5.model.Doctor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DoctorService {
    private DoctorDao doctorDao = new DoctorDao();

    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    public boolean addDoctor(Doctor doctor) {
        if (doctor.getId() == null || doctor.getId().trim().isEmpty()) {
            System.err.println("Lỗi: Mã bác sĩ không được để trống.");
            return false;
        }
        if (doctor.getName() == null || doctor.getName().trim().isEmpty()) {
            System.err.println("Lỗi: Tên bác sĩ không được để trống.");
            return false;
        }
        if (doctor.getSpecialty() == null || doctor.getSpecialty().trim().isEmpty()) {
            System.err.println("Lỗi: Chuyên khoa không được để trống.");
            return false;
        }
        
        try {
            return doctorDao.addDoctor(doctor);
        } catch (SQLException e) {
            String sqlState = e.getSQLState();
            if (sqlState != null) {
                if (sqlState.startsWith("23")) {
                    System.err.println("Lỗi Cơ sở dữ liệu: Mã bác sĩ '" + doctor.getId() + "' đã tồn tại trong hệ thống (Trùng khóa chính).");
                } else if (sqlState.startsWith("22")) {
                    System.err.println("Lỗi Cơ sở dữ liệu: Dữ liệu bạn nhập vào vượt quá độ dài cho phép của bảng.");
                } else {
                    System.err.println("Lỗi Cơ sở dữ liệu (" + sqlState + "): " + e.getMessage());
                }
            } else {
                System.err.println("Lỗi không xác định khi thêm bác sĩ: " + e.getMessage());
            }
            return false;
        }
    }

    public Map<String, Integer> getStatisticsBySpecialty() {
        return doctorDao.getStatisticsBySpecialty();
    }
}
