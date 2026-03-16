package sesion07.bt5;

import java.util.ArrayList;
import java.util.List;

class DatabaseOrderRepository implements OrderRepository {
    private List<Order> db = new ArrayList<>();
    @Override public void save(Order o) { db.add(o); System.out.println("Đã lưu đơn " + o.orderId + " vào DB."); }
    @Override public List<Order> findAll() { return db; }
}
