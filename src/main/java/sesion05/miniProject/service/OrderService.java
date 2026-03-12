package sesion05.miniProject.service;

import java.util.List;

import sesion05.miniProject.model.Order;
import sesion05.miniProject.model.OrderStatus;
import sesion05.miniProject.repository.OrderRepository;

public class OrderService {
    private OrderRepository repository = new OrderRepository();

    public void createOrder(Order order) {
        repository.addOrder(order);
    }

    public List<Order> getOrders() {
        return repository.getAllOrders();
    }

    public double calculateRevenue() {

        return repository.getAllOrders()
                .stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::calculateTotal)
                .sum();

    }

    public void addOrder(Order order) {
        repository.addOrder(order);
    }
}
