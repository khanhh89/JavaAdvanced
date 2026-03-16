package sesion07.bt1;
import java.text.NumberFormat;
import java.util.*;
public class Run {
    public static void main(String[] args) {
        Product p1,p2;

        p1 = new Product("SP01","Laptop",15000000);
        p2 = new Product("SP02","Chuột",300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");
        // Cần phải có class ProductService, cài đặt hàm addProduct(Product p) để
        // lưu product vào trong collection kiểu Product


        Customer c1 = new Customer("Nguyễn Văn A","a@example.com","Hà Nội");
        System.out.println("Đã thêm khách hàng");
        //Cần phải có class CustomerService chứa phương thức thêm khách hàng để lưu vào 1
        //collection

        List<Product> listPO1 = new ArrayList<Product>();
        listPO1.add(p1);
        listPO1.add(p2);
        listPO1.add(p2);

        OrderCalculator oc = new OrderCalculator();
        Order o1 = new Order("ORD001",c1,listPO1,0);
        System.out.println("Đơn hàng ORD001 đã được tạo");

        System.out.println("Tổng tiền: "+ NumberFormat.getNumberInstance().format(oc.calculateTotalMoneyForOrder(o1)));

        OrderRepository repo = new OrderRepository();
        repo.saveOrder(o1);
        System.out.println("Đã lưu đơn hàng "+o1.getOrderId());

        EmailService es = new EmailService();
        es.sendEmail(o1);
    }
}