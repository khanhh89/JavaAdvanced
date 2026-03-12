package sesion05.miniProject.model;

public class Dessert extends MenuItem{
    public Dessert(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
