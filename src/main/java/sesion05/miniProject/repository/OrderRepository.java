package sesion05.miniProject.repository;

import java.util.ArrayList;
import java.util.List;

import sesion05.miniProject.model.Order;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

}
