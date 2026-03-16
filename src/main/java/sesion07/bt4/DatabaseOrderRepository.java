package sesion07.bt4;

class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào database: " + order.getId());
    }
}
