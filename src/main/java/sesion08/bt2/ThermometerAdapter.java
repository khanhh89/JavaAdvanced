package sesion08.bt2;

public class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int fahrenheit = oldThermometer.getTemperatureFahrenheit();
        // Chuyển đổi công thức: (F - 32) * 5 / 9
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}
