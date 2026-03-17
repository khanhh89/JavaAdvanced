package sesion08.bt3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RemoteControl {
    private Map<Integer, Command> slots = new HashMap<>();
    private Stack<Command> undoStack = new Stack<>();

    public void setCommand(int slot, Command command) {
        slots.put(slot, command);
        System.out.println("Đã gán " + command.getClass().getSimpleName() + " cho nút " + slot);
    }

    public void buttonWasPressed(int slot) {
        Command command = slots.get(slot);
        if (command != null) {
            System.out.println("Nhấn nút " + slot);
            command.execute();
            undoStack.push(command);
        } else {
            System.out.println("Nút " + slot + " chưa được gán lệnh.");
        }
    }

    public void undoButtonWasPressed() {
        System.out.println("Nhấn Undo");
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
        } else {
            System.out.println("Không có lệnh nào để Undo.");
        }
    }
}
