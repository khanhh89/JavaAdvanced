package sesion08.bt3;

public class AirConditioner {
    private int temperature = 25; // Nhiệt độ mặc định

    public void setTemperature(int temperature, boolean isUndo) {
        this.temperature = temperature;
        if (isUndo) {
            System.out.println("Undo: Điều hòa: Nhiệt độ = " + temperature + " (nhiệt độ cũ)");
        } else {
            System.out.println("Điều hòa: Nhiệt độ = " + temperature);
        }
    }

    public int getTemperature() {
        return temperature;
    }
}
