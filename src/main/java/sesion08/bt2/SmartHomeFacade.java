package sesion08.bt2;

public class SmartHomeFacade {
    private Light light;
    private Fan fan;
    private AirConditioner ac;
    private TemperatureSensor temperatureSensor;
    private OldThermometer oldThermometer;

    public SmartHomeFacade(Light light, Fan fan, AirConditioner ac, TemperatureSensor temperatureSensor, OldThermometer oldThermometer) {
        this.light = light;
        this.fan = fan;
        this.ac = ac;
        this.temperatureSensor = temperatureSensor;
        this.oldThermometer = oldThermometer;
    }

    public void leaveHome() {
        light.turnOff();
        fan.turnOff();
        ac.turnOff();
    }

    public void sleepMode() {
        light.turnOff();
        ac.setTemperature(28);
        fan.setLowSpeed();
    }

    public void getCurrentTemperature() {
        double tempC = temperatureSensor.getTemperatureCelsius();
        int tempF = oldThermometer.getTemperatureFahrenheit();
        System.out.printf("Nhiệt độ hiện tại: %.1f°C (chuyển đổi từ %d°F)\n", tempC, tempF);
    }
}
