package sesion05.miniProject.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
     private String orderId;
    private OrderStatus status;
    private Map<MenuItem, Integer> items;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = OrderStatus.PENDING;
        this.items = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, quantity);
    }

    public double calculateTotal() {
        return items.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().calculatePrice() * e.getValue())
                .sum();

    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }
}
