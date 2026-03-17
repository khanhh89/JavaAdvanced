package sesion08.bt3;

public class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn(false);
    }

    @Override
    public void undo() {
        fan.turnOff(true);
    }
}
