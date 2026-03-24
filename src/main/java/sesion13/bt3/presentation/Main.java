package sesion13.bt3.presentation;

import sesion13.bt3.dao.DischargeDao;

public class Main {
    public static void main(String[] args) {
        DischargeDao dischargeDao = new DischargeDao();

        System.out.println("=========================================");
        System.out.println(" KỊCH BẢN 1: BỆNH NHÂN ĐỦ TIỀN");
        System.out.println("=========================================");
        try {
            dischargeDao.xuatVienVaThanhToan(1, 500000);
        } catch (Exception e) {
            System.err.println("Lý do thất bại: " + e.getMessage());
        }

        System.out.println("\n=========================================");
        System.out.println(" KỊCH BẢN 2: BẪY SỐ 1 - KHÔNG ĐỦ TIỀN");
        System.out.println("=========================================");
        try {
            dischargeDao.xuatVienVaThanhToan(2, 999999999);
        } catch (Exception e) {
            System.err.println("Lý do thất bại (Lỗi Nghiệp vụ): " + e.getMessage());
        }

        System.out.println("\n=========================================");
        System.out.println(" KỊCH BẢN 3: BẪY SỐ 2 - BỆNH NHÂN ẢO");
        System.out.println("=========================================");
        try {
            dischargeDao.xuatVienVaThanhToan(9999, 100000);
        } catch (Exception e) {
            System.err.println("Lý do thất bại (Lỗi Hệ thống): " + e.getMessage());
        }
    }
}
