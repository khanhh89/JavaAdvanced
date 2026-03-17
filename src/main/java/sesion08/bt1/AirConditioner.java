package sesion08.bt1;

public class AirConditioner implements Device {
    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật lạnh.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}
