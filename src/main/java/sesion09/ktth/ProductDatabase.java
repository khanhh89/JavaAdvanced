package sesion09.ktth;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;
    private ProductDatabase() {
        products = new ArrayList<>();
    }
    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void deleteProduct(String id){
        products.removeIf(product -> product.getId().equals(id));
    }
    public Product findById(String id){
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getAllProducts() {
        return products;
    }
}
