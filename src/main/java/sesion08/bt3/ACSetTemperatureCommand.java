package sesion08.bt3;

public class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int newTemperature;
    private int oldTemperature;

    public ACSetTemperatureCommand(AirConditioner ac, int newTemperature) {
        this.ac = ac;
        this.newTemperature = newTemperature;
    }

    @Override
    public void execute() {
        this.oldTemperature = ac.getTemperature();
        ac.setTemperature(newTemperature, false);
    }

    @Override
    public void undo() {
        ac.setTemperature(oldTemperature, true);
    }
}
