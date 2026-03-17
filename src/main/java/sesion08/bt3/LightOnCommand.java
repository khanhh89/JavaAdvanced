package sesion08.bt3;

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn(false);
    }

    @Override
    public void undo() {
        light.turnOff(true);
    }
}
