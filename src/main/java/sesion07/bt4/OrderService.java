package sesion07.bt4;

class OrderService {
    private final OrderRepository repository;
    private final NotificationService notification;
    public OrderService(OrderRepository repository, NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(String orderId) {
        Order order = new Order(orderId);
        repository.save(order);
        notification.send("Đơn hàng " + orderId + " đã được tạo", "Khách hàng A");
    }
}