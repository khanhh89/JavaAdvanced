package sesion12.bt5.business;

import sesion12.bt5.dao.PatientDao;
import sesion12.bt5.model.Patient;

import java.util.List;

public class PatientService {
    private PatientDao patientDao = new PatientDao();

    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    public boolean addPatient(Patient patient) {
        if (patient.getId() == null || patient.getId().isEmpty()) {
            System.err.println("Lỗi: Mã bệnh nhân không được để trống!");
            return false;
        }
        if (patient.getName() == null || patient.getName().isEmpty()) {
            System.err.println("Lỗi: Tên bệnh nhân không được để trống!");
            return false;
        }
        return patientDao.addPatient(patient);
    }

    public boolean updatePathology(String id, String pathology) {
        if (id == null || id.isEmpty()) {
            System.err.println("Lỗi: Mã bệnh nhân không hợp lệ!");
            return false;
        }
        return patientDao.updatePathology(id, pathology);
    }

    public double calculateDischargeFee(String patientId) {
        return patientDao.calculateDischargeFee(patientId);
    }
}
