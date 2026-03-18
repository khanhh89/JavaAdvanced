package sesion09.miniPrj;

public class StandardVehicle extends Vehicle{
    public StandardVehicle(String name, Intersection i) {
        super(name, i);
    }

    // xe thường → không ưu tiên
    @Override
    public boolean isPriority() {
        return false;
    }
}
