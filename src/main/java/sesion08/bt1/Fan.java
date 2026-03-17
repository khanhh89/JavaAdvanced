package sesion08.bt1;

public class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật chạy.");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Tắt.");
    }
}
