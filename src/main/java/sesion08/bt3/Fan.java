package sesion08.bt3;

public class Fan {
    public void turnOn(boolean isUndo) {
        if (isUndo) {
            System.out.println("Undo: Quạt Bật (quay lại trạng thái trước)");
        } else {
            System.out.println("Quạt: Bật");
        }
    }

    public void turnOff(boolean isUndo) {
        if (isUndo) {
            System.out.println("Undo: Quạt Tắt (quay lại trạng thái trước)");
        } else {
            System.out.println("Quạt: Tắt");
        }
    }
}
