package sesion07.bt5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        OrderRepository repo = new DatabaseOrderRepository();
        NotificationService notify = new EmailNotification();
        OrderService orderService = new OrderService(repo, notify);

        while (true) {
            System.out.println("1. Thêm sản phẩm mới vào kho");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Tạo đơn hàng và thanh toán");
            System.out.println("4. Xem danh sách tất cả đơn hàng");
            System.out.println("5. Thống kê tổng doanh thu hệ thống");
            System.out.println("8. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    products.add(new Product("SP01", "Laptop", 15000000, "Điện tử"));
                    System.out.println("Đã thêm SP01");
                    break;
                case 2:
                    customers.add(new Customer("Nguyễn Văn A", "a@example.com", "0123"));
                    System.out.println("Đã thêm khách hàng");
                    break;
                case 3:
                    if (products.isEmpty() || customers.isEmpty()) break;
                    Order order = new Order("ORD001", customers.get(0));
                    order.products.add(products.get(0));
                    orderService.processOrder(order, new PercentageDiscount(10), new CreditCardPayment());
                    InvoiceGenerator.print(order);
                    break;
                case 4:
                    repo.findAll().forEach(o -> System.out.println(o.orderId + " - " + o.customer.name + " - " + o.finalAmount));
                    break;
                case 5:
                    double revenue = repo.findAll().stream().mapToDouble(o -> o.finalAmount).sum();
                    System.out.println("Tổng doanh thu: " + revenue);
                    break;
                case 8: return;
            }
        }
    }
}
