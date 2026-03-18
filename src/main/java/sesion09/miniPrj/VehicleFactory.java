package sesion09.miniPrj;

import java.util.Random;

public class VehicleFactory {
    private static int count = 0;
    private static Random random = new Random();

    public static Vehicle create(Intersection intersection) {
        count++;
        // Ngẫu nhiên tạo xe ưu tiên (20% cơ hội) hoặc xe thường
        if (random.nextInt(5) == 0) {
            return new PriorityVehicle("Xe Ưu Tiên " + count, intersection);
        } else {
            return new StandardVehicle("Xe " + count, intersection);
        }
    }
}

class PriorityVehicle extends Vehicle {
    public PriorityVehicle(String name, Intersection i) {
        super(name, i);
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
