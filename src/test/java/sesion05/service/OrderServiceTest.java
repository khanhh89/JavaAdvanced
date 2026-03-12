package sesion05.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sesion05.miniProject.model.Food;
import sesion05.miniProject.model.MenuItem;
import sesion05.miniProject.model.Order;
import sesion05.miniProject.model.OrderStatus;
import sesion05.miniProject.service.OrderService;

public class OrderServiceTest {
     @Test
    void calculateRevenue() {
        OrderService service = new OrderService();

        Order order = new Order("O1");

        MenuItem burger = new Food("F1","Burger",50000);

        order.addItem(burger,2);

        order.setStatus(OrderStatus.PAID);

        service.addOrder(order);

        double revenue = service.calculateRevenue();

        assertEquals(100000, revenue);
    }
}
