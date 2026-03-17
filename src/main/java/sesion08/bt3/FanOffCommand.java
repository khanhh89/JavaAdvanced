package sesion08.bt3;

public class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff(false);
    }

    @Override
    public void undo() {
        fan.turnOn(true);
    }
}
