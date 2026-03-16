package sesion07.bt5;
import java.util.*;
class Order {
    String orderId;
    Customer customer;
    List<Product> products = new ArrayList<>();
    double totalAmount, finalAmount;
    public Order(String id, Customer c) { this.orderId = id; this.customer = c; }
}
