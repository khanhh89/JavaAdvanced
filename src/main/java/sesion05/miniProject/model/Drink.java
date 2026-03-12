package sesion05.miniProject.model;

public class Drink extends MenuItem {
    private String size;

    public Drink(String id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        switch (size) {
            case "M":
                return getPrice() + 5000;
            case "L":
                return getPrice() + 10000;
            default:
                return getPrice();
        }
    }
}
