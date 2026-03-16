package sesion07.bt5;

class OrderService {
    private final OrderRepository repo;
    private final NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo;
        this.notify = notify;
    }

    public void processOrder(Order order, DiscountStrategy discount, PaymentMethod payment) {
        order.totalAmount = order.products.stream().mapToDouble(p -> p.price).sum();
        order.finalAmount = discount.applyDiscount(order.totalAmount);

        payment.pay(order.finalAmount);
        repo.save(order);
        notify.send("Đơn hàng " + order.orderId + " thành công.", order.customer.email);
    }
}
