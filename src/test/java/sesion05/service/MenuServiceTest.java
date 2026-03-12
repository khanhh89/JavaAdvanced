package sesion05.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sesion05.miniProject.model.Food;
import sesion05.miniProject.model.MenuItem;
import sesion05.miniProject.service.MenuService;

public class MenuServiceTest {
     @Test
    void addItem() {
        MenuService menuService = new MenuService();
        MenuItem burger = new Food("F1", "Burger", 50000);
        menuService.addItem(burger);
        assertEquals(1, menuService.getMenu().size());
    }
}
