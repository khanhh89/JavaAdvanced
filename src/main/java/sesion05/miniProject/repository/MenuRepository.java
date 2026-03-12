package sesion05.miniProject.repository;

import java.util.ArrayList;
import java.util.List;

import sesion05.miniProject.model.MenuItem;

public class MenuRepository {
     private List<MenuItem> menuList = new ArrayList<>();

    public void add(MenuItem item) {
        menuList.add(item);
    }

    public List<MenuItem> getAll() {
        return menuList;
    }
}
