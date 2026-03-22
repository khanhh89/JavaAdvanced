package demo.ra;

import demo.ra.business.User;
import demo.ra.entity.UserBusiness;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserBusiness userBusiness = UserBusiness.getInstance();

        while (true) {
            System.out.println("1. Hiển thị danh sách người dùng");
            System.out.println("2. Thêm mới người dùng");
            System.out.println("3. Tìm kiếm người dùng theo tên");
            System.out.println("4. Xóa người dùng theo ID");
            System.out.println("5. Lọc danh sách ADMIN");
            System.out.println("6. Sắp xếp người dùng theo điểm giảm dần");
            System.out.println("7. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    displayUsers(userBusiness.getAll());
                    break;
                case 2:
                    addUser(sc, userBusiness);
                    break;
                case 3:
                    System.out.print("Nhập tên cần tìm: ");
                    String name = sc.nextLine();
                    displayUsers(userBusiness.searchByName(name));
                    break;
                case 4:
                    System.out.print("Nhập mã ID cần xóa: ");
                    String idDelete = sc.nextLine();
                    if (userBusiness.delete(idDelete)) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không tìm thấy ID!");
                    }
                    break;
                case 5:
                    displayUsers(userBusiness.filterAdmin());
                    break;
                case 6:
                    displayUsers(userBusiness.getSortedByScore());
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayUsers(List<User> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.printf("| %-10s | %-20s | %-5s | %-10s | %-5s |\n", "ID", "Name", "Age", "Role", "Score");
        System.out.println("------------------------------------------------------------------");
        for (User u : list) {
            u.displayInfor();
        }
    }

    private static void addUser(Scanner sc, UserBusiness business) {
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập vai trò (ADMIN/USER): ");
        String role = sc.nextLine();
        System.out.print("Nhập điểm: ");
        double score = Double.parseDouble(sc.nextLine());

        User user = new User(id, name, age, role, score);
        if (business.add(user)) {
            System.out.println("Thêm mới thành công!");
        } else {
            System.out.println("ID đã tồn tại!");
        }
    }
}
