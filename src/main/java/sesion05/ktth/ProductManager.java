package sesion05.ktth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ProductManager {
    private List<Product> products = new ArrayList<>();
    
    public void addProduct(Product p) throws InvalidProductException {
        if (products.stream().anyMatch(prod -> prod.getId() == p.getId())) {
            throw new InvalidProductException("ID bị trùng, không thể thêm!");
        }
        products.add(p);
    }
    
    public void displayProducts() {
        System.out.printf("%-5s %-15s %-10s %-10s %-15s\n",
                "ID", "Name", "Price", "Quantity", "Category");
        products.forEach(System.out::println);
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Product product = products.stream().filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new InvalidProductException("Khong tim thay san pham"));
        product.setQuantity(newQuantity);
    }

    public void updateQuantity1(int id, int quantity) throws InvalidProductException {
        Product product = products.stream().filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new InvalidProductException("Khong tim thay san pham"));
        product.setQuantity(product.getQuantity() + quantity);
    }

    public void deleteOutOfStock() {
        products = products.stream()
                .filter(p -> p.getQuantity() > 0)
                .collect(Collectors.toList());
    }
}
