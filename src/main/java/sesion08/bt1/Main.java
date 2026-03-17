package sesion08.bt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();
        HardwareConnection hardwareConnection = HardwareConnection.getInstance();
        while (true) {
            System.out.println("\n1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập số!");
                scanner.next(); 
                continue;
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hardwareConnection.connect();
                    break;
                case 2:
                    System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa");
                    System.out.print("Chọn: ");
                    int type = scanner.nextInt();
                    DeviceFactory factory = null;
                    if (type == 1) factory = new LightFactory();
                    else if (type == 2) factory = new FanFactory();
                    else if (type == 3) factory = new AirConditionerFactory();

                    if (factory != null) {
                        devices.add(factory.createDevice());
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;
                case 3:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị nào được tạo.");
                        break;
                    }
                    System.out.print("Chọn thiết bị vừa tạo: ");
                    int deviceIndex = scanner.nextInt();
                    if (deviceIndex >= 1 && deviceIndex <= devices.size()) {
                        Device device = devices.get(deviceIndex - 1);
                        device.turnOn();
                    } else {
                        System.out.println("Thiết bị không tồn tại.");
                    }
                    break;
                case 4:
                    hardwareConnection.disconnect();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
