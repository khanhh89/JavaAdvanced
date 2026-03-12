package sesion05.miniProject.service;

import java.util.List;
import java.util.stream.Collectors;

import sesion05.miniProject.model.MenuItem;
import sesion05.miniProject.repository.MenuRepository;

public class MenuService {
    private MenuRepository repository = new MenuRepository();

    public void addItem(MenuItem item) {
        repository.add(item);
    }

    public List<MenuItem> getMenu() {
        return repository.getAll();
    }

    public List<MenuItem> searchByName(String keyword) {

        return repository.getAll()
                .stream()
                .filter(item -> item.getName()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

    }
}
