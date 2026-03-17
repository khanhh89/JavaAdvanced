package sesion08.bt3;

public class Light {
    public void turnOn(boolean isUndo) {
        if (isUndo) {
            System.out.println("Undo: Đèn Bật (quay lại trạng thái trước)");
        } else {
            System.out.println("Đèn: Bật");
        }
    }

    public void turnOff(boolean isUndo) {
        if (isUndo) {
            System.out.println("Undo: Đèn Tắt (quay lại trạng thái trước)");
        } else {
            System.out.println("Đèn: Tắt");
        }
    }
}
