package sesion07.bt5;
import java.util.List;

public interface OrderRepository {
    void save(Order order); List<Order> findAll();
}
