package sesion07.bt1;
import java.util.*;
public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public Order findOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

}
