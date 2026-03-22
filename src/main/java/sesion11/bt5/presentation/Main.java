package sesion11.bt5.presentation;

import sesion11.bt5.business.DoctorService;
import sesion11.bt5.model.Doctor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();

        while (true) {
            System.out.println("   HỆ THỐNG QUẢN LÝ BỆNH VIỆN RIKKEI-CARE");
            System.out.println("1. Xem danh sách bác sĩ trực ca");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lỗi: Vui lòng nhập số.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\n--- DANH SÁCH BÁC SĨ ---");
                    List<Doctor> doctors = doctorService.getAllDoctors();
                    if (doctors.isEmpty()) {
                        System.out.println("Chưa có bác sĩ nào trong hệ thống.");
                    } else {
                        System.out.printf("%-15s | %-25s | %-20s\n", "Mã Bác Sĩ", "Họ Tên", "Chuyên Khoa");
                        System.out.println("------------------------------------------------------------------");
                        for (Doctor d : doctors) {
                            System.out.printf("%-15s | %-25s | %-20s\n", d.getId(), d.getName(), d.getSpecialty());
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- THÊM BÁC SĨ MỚI ---");
                    System.out.print("Nhập mã bác sĩ (VD: BS01): ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập họ tên (VD: Nguyễn Văn A): ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập chuyên khoa (VD: Nội khoa): ");
                    String specialty = scanner.nextLine();

                    Doctor newDoctor = new Doctor(id, name, specialty);
                    boolean isAdded = doctorService.addDoctor(newDoctor);
                    if (isAdded) {
                        System.out.println("=> THÀNH CÔNG: Đã thêm bác sĩ vào hệ thống!");
                    } else {
                        System.out.println("=> THẤT BẠI: Thêm bác sĩ không thành công. Hãy xem lỗi ở trên.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- THỐNG KÊ BÁC SĨ THEO CHUYÊN KHOA ---");
                    Map<String, Integer> stats = doctorService.getStatisticsBySpecialty();
                    if (stats.isEmpty()) {
                        System.out.println("Không có dữ liệu để thống kê.");
                    } else {
                        System.out.printf("%-20s | %-10s\n", "Chuyên Khoa", "Số Lượng Bác Sĩ");
                        System.out.println("---------------------------------------");
                        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                            System.out.printf("%-20s | %-10d\n", entry.getKey(), entry.getValue());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Đang đóng ứng dụng... Cảm ơn bạn đã sử dụng hệ thống Rikkei-Care!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lỗi: Chức năng không hợp lệ. Vui lòng chọn từ 1 đến 4.");
            }
        }
    }
}
