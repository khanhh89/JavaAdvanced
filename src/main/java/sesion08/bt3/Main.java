package sesion08.bt3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- ĐIỀU KHIỂN TỪ XA ---");
            System.out.println("1. Gán nút: Bật đèn");
            System.out.println("2. Gán nút: Tắt đèn");
            System.out.println("3. Gán nút: Bật quạt");
            System.out.println("4. Gán nút: Tắt quạt");
            System.out.println("5. Gán nút: Set điều hòa");
            System.out.println("6. Nhấn nút đã gán");
            System.out.println("7. Nhấn Undo");
            System.out.println("8. Thoát");
            System.out.print("Chọn thao tác: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập số hợp lệ.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập vị trí nút muốn gán (VD: 1, 2...): ");
                    int slot1 = scanner.nextInt();
                    remote.setCommand(slot1, new LightOnCommand(light));
                    break;
                case 2:
                    System.out.print("Nhập vị trí nút muốn gán: ");
                    int slot2 = scanner.nextInt();
                    remote.setCommand(slot2, new LightOffCommand(light));
                    break;
                case 3:
                    System.out.print("Nhập vị trí nút muốn gán: ");
                    int slot3 = scanner.nextInt();
                    remote.setCommand(slot3, new FanOnCommand(fan));
                    break;
                case 4:
                    System.out.print("Nhập vị trí nút muốn gán: ");
                    int slot4 = scanner.nextInt();
                    remote.setCommand(slot4, new FanOffCommand(fan));
                    break;
                case 5:
                    System.out.print("Nhập vị trí nút muốn gán: ");
                    int slot5 = scanner.nextInt();
                    System.out.print("Nhập nhiệt độ muốn cài đặt (°C): ");
                    int temp = scanner.nextInt();
                    remote.setCommand(slot5, new ACSetTemperatureCommand(ac, temp));
                    break;
                case 6:
                    System.out.print("Nhập vị trí nút muốn nhấn: ");
                    int slotToPress = scanner.nextInt();
                    remote.buttonWasPressed(slotToPress);
                    break;
                case 7:
                    remote.undoButtonWasPressed();
                    break;
                case 8:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
