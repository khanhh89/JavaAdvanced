package sesion05.miniProject.ui;

import java.util.List;
import java.util.Scanner;

import sesion05.miniProject.model.Dessert;
import sesion05.miniProject.model.Drink;
import sesion05.miniProject.model.Food;
import sesion05.miniProject.model.MenuItem;
import sesion05.miniProject.model.Order;
import sesion05.miniProject.model.OrderStatus;
import sesion05.miniProject.service.MenuService;
import sesion05.miniProject.service.OrderService;

public class ConsoleMenu {

    private MenuService menuService = new MenuService();
    private OrderService orderService = new OrderService();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== QUẢN LÝ CỬA HÀNG ĐỒ ĂN NHANH =====");
            System.out.println("1. Thêm món ăn vào menu");
            System.out.println("2. Hiển thị menu");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. Thống kê doanh thu");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addMenuItem();
                    break;

                case 2:
                    showMenuItems();
                    break;

                case 3:
                    createOrder();
                    break;

                case 4:
                    showOrders();
                    break;

                case 5:
                    showRevenue();
                    break;

                case 0:
                    System.out.println("Thoát chương trình...");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }

    }

    private void addMenuItem() {

        System.out.println("\n--- Thêm món ăn ---");

        System.out.print("Nhập ID: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên món: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Chọn loại món:");
        System.out.println("1. Món chính");
        System.out.println("2. Đồ uống");
        System.out.println("3. Tráng miệng");

        int type = sc.nextInt();
        sc.nextLine();

        MenuItem item = null;

        switch (type) {

            case 1:
                item = new Food(id, name, price);
                break;

            case 2:
                System.out.print("Nhập size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, price, size);
                break;

            case 3:
                item = new Dessert(id, name, price);
                break;

            default:
                System.out.println("Loại món không hợp lệ!");
                return;
        }

        menuService.addItem(item);
        System.out.println("Đã thêm món thành công!");

    }

    private void showMenuItems() {

        System.out.println("\n--- DANH SÁCH MENU ---");

        List<MenuItem> menu = menuService.getMenu();

        if (menu.isEmpty()) {
            System.out.println("Menu đang trống!");
            return;
        }

        for (MenuItem item : menu) {

            System.out.println(
                    item.getId() + " | "
                            + item.getName() + " | "
                            + item.calculatePrice());

        }

    }

    private void createOrder() {

        System.out.println("\n--- TẠO ĐƠN HÀNG ---");

        System.out.print("Nhập ID đơn hàng: ");
        String orderId = sc.nextLine();

        Order order = new Order(orderId);

        while (true) {

            showMenuItems();

            System.out.print("Nhập ID món muốn thêm (0 để kết thúc): ");
            String itemId = sc.nextLine();

            if (itemId.equals("0"))
                break;

            MenuItem item = findMenuItem(itemId);

            if (item == null) {
                System.out.println("Không tìm thấy món!");
                continue;
            }

            System.out.print("Nhập số lượng: ");
            int qty = sc.nextInt();
            sc.nextLine();

            order.addItem(item, qty);

            System.out.println("Đã thêm vào đơn!");

        }

        orderService.createOrder(order);
        order.setStatus(OrderStatus.PAID);
        System.out.println("Tạo đơn thành công!");
        System.out.println("Tổng tiền: " + order.calculateTotal());

    }

    private MenuItem findMenuItem(String id) {

        List<MenuItem> menu = menuService.getMenu();

        for (MenuItem item : menu) {

            if (item.getId().equals(id))
                return item;

        }

        return null;

    }

    private void showOrders() {

        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");

        List<Order> orders = orderService.getOrders();

        if (orders.isEmpty()) {
            System.out.println("Chưa có đơn hàng!");
            return;
        }

        for (Order order : orders) {

            System.out.println(
                    "Order ID: " + order.getOrderId()
                            + " | Tổng tiền: " + order.calculateTotal()
                            + " | Trạng thái: " + order.getStatus());

        }

    }

    private void showRevenue() {

        double revenue = orderService.calculateRevenue();

        System.out.println("\nTổng doanh thu: " + revenue);

    }

}