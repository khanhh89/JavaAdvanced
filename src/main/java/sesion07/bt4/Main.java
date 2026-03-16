package sesion07.bt4;

public class Main {
        public static void main(String[] args) {
            System.out.println("Cấu hình 1");
            OrderRepository fileRepo = new FileOrderRepository();
            NotificationService email = new EmailService();

            OrderService service1 = new OrderService(fileRepo, email);
            service1.createOrder("ORD001");

            System.out.println("\nCấu hình 2");
            OrderRepository dbRepo = new DatabaseOrderRepository();
            NotificationService sms = new SMSNotification();

            OrderService service2 = new OrderService(dbRepo, sms);
            service2.createOrder("ORD002");
        }

}
