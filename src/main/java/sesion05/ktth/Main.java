package sesion05.ktth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");;
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập giá: ");
                        double price = sc.nextDouble();
                        System.out.print("Nhập số lượng: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nhập danh mục: ");
                        String category = sc.nextLine();
                        manager.addProduct(new Product(id, name, price, quantity, category));
                        break;
                    case 2:
                        manager.displayProducts();
                        break;
                    case 3:
                        System.out.print("Nhập ID cần cập nhật: ");
                        int updateId = sc.nextInt();
                        System.out.print("Nhập số lượng mới: ");
                        int newQuantity = sc.nextInt();
                        manager.updateQuantity(updateId, newQuantity);
                        break;
                    case 4:
                        manager.deleteOutOfStock();
                        System.out.println("Đã xóa các sản phẩm hết hàng!");
                        break;
                    case 5:
                        System.out.println("Thoát chương trình...");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (InvalidProductException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
