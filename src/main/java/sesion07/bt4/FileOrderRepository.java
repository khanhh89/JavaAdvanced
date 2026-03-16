package sesion07.bt4;

class FileOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào file: " + order.getId());
    }
}
