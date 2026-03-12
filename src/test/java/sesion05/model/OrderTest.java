package sesion05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sesion05.miniProject.model.Drink;
import sesion05.miniProject.model.Food;
import sesion05.miniProject.model.MenuItem;
import sesion05.miniProject.model.Order;

public class OrderTest {
    @Test
    void addItem() {
        Order order = new Order("O1");

        MenuItem burger = new Food("F1", "Burger", 50000);
        MenuItem coke = new Drink("D1", "Coke", 10000, "M");

        order.addItem(burger, 2);
        order.addItem(coke, 1);

        double total = order.calculateTotal();

        assertEquals(115000, total);
    }
}
