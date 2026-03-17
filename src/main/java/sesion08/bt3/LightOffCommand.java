package sesion08.bt3;

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff(false);
    }

    @Override
    public void undo() {
        light.turnOn(true);
    }
}
