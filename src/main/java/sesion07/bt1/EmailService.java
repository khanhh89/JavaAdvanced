package sesion07.bt1;

public class EmailService {
    public void sendEmail(Order order) {
        System.out.println("Đã gửi email đến: "+order.getCustomer().getEmail()+": Đơn hàng "+order.getOrderId()+" đã được tạo");
    }
}
