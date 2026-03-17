package sesion09.ktth;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    @Override
    public void displayInfor(){
        System.out.println("PhysicalProduct: ID: "+ getId() + ", Name: " + getName() + ", Price: " + getPrice() + ", Weight: " + weight);
    }
}
