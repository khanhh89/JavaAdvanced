package sesion09.ktth;

public class DigitalProduct extends  Product {
    private double size;
    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }
    @Override
    public void displayInfor() {
        System.out.println("DigitalProduct: ID: " + getId() + ", Name: " + getName() + ", Price: " + getPrice() + ", Size: " + size);
    }

}
