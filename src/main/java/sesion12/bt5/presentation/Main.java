package sesion12.bt5.presentation;

import sesion12.bt5.business.PatientService;
import sesion12.bt5.model.Patient;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientService patientService = new PatientService();

        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("   HỆ THỐNG QUẢN LÝ NỘI TRÚ RIKKEI-HOSPITAL (RHMS)   ");
            System.out.println("=======================================================");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & Tính phí");
            System.out.println("5. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lỗi: Vui lòng nhập số hợp lệ.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Bỏ qua Enter thừa

            switch (choice) {
                case 1:
                    System.out.println("\n--- DANH SÁCH BỆNH NHÂN NỘI TRÚ ---");
                    List<Patient> patients = patientService.getAllPatients();
                    if (patients.isEmpty()) {
                        System.out.println("Không có bệnh nhân nào trong hệ thống.");
                    } else {
                        System.out.printf("%-10s | %-25s | %-5s | %-20s | %-25s | %-10s\n", 
                                "Mã BN", "Tên Bệnh Nhân", "Tuổi", "Khoa", "Bệnh án", "Số ngày NV");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                        for (Patient p : patients) {
                            System.out.printf("%-10s | %-25s | %-5d | %-20s | %-25s | %-10d\n", 
                                    p.getId(), p.getName(), p.getAge(), p.getDepartment(), p.getPathology(), p.getAdmissionDays());
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- TIẾP NHẬN BỆNH NHÂN MỚI ---");
                    System.out.print("Nhập Mã BN: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập Tên BN (hỗ trợ tên có dấu nháy như D'Arcy, L'Oréal): ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập Tuổi: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập Khoa điều trị: ");
                    String department = scanner.nextLine();
                    System.out.print("Nhập Bệnh án ban đầu: ");
                    String pathology = scanner.nextLine();
                    System.out.print("Nhập số ngày dự kiến (hoặc 0 nếu mới nhập): ");
                    int days = scanner.nextInt();

                    Patient newPatient = new Patient(id, name, age, department, pathology, days);
                    if (patientService.addPatient(newPatient)) {
                        System.out.println("=> THÀNH CÔNG: Đã thêm bệnh nhân vào hệ thống!");
                    } else {
                        System.out.println("=> THẤT BẠI: Thêm bệnh nhân không thành công.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- CẬP NHẬT BỆNH ÁN ---");
                    System.out.print("Nhập Mã BN cần cập nhật: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Nhập thông tin bệnh án / tiến triển mới: ");
                    String newPathology = scanner.nextLine();

                    if (patientService.updatePathology(updateId, newPathology)) {
                        System.out.println("=> THÀNH CÔNG: Đã cập nhật bệnh án.");
                    } else {
                        System.out.println("=> THẤT BẠI: Mã bệnh nhân không tồn tại hoặc có lỗi xảy ra.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- XUẤT VIỆN & TÍNH PHÍ ---");
                    System.out.print("Nhập Mã BN cần xuất viện: ");
                    String dischargeId = scanner.nextLine();

                    double totalFee = patientService.calculateDischargeFee(dischargeId);
                    if (totalFee >= 0) {
                        System.out.printf("=> THÀNH CÔNG: Tổng viện phí của BN '%s' là: %,.0f VNĐ\n", dischargeId, totalFee);
                    } else {
                        System.out.println("=> THẤT BẠI: Không tìm thấy BN hoặc có lỗi hệ thống.");
                    }
                    break;
                case 5:
                    System.out.println("Đang thoát hệ thống RHMS... Tạm biệt!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lỗi: Chức năng không hợp lệ!");
            }
        }
    }
}
