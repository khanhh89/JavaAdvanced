package sesion08.bt2;

public class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật chạy");
    }

    @Override
    public void turnOff() {
        System.out.println("FACADE: Tắt quạt");
    }

    public void setLowSpeed() {
        System.out.println("FACADE: Quạt chạy tốc độ thấp");
    }
}
