package sesion07.bt1;
import java.util.*;
public class Order {
    private String orderId;
    private Customer customer;
    private List<Product> products;
    private double totalMoney;

    public Order() {
    }

    public Order(String orderId, Customer customer, List<Product> products, double totalMoney) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalMoney = totalMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
