package sesion08.bt4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TemperatureSensor sensor = new TemperatureSensor();
        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        boolean fanRegistered = false;
        boolean humidifierRegistered = false;
        while (true) {
            System.out.println("\n--- HỆ THỐNG THEO DÕI NHIỆT ĐỘ ---");
            System.out.println("1. Đăng ký quạt và máy tạo ẩm");
            System.out.println("2. Set nhiệt độ");
            System.out.println("3. Hủy đăng ký quạt");
            System.out.println("4. Hủy đăng ký máy tạo ẩm");
            System.out.println("5. Thoát");
            System.out.print("Chọn thao tác: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập số hợp lệ.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (!fanRegistered) {
                        sensor.attach(fan);
                        fanRegistered = true;
                        System.out.println("Quạt: Đã đăng ký nhận thông báo");
                    } else {
                        System.out.println("Quạt đã được đăng ký từ trước.");
                    }
                    if (!humidifierRegistered) {
                        sensor.attach(humidifier);
                        humidifierRegistered = true;
                        System.out.println("Máy tạo ẩm: Đã đăng ký");
                    } else {
                        System.out.println("Máy tạo ẩm đã được đăng ký từ trước.");
                    }
                    break;
                case 2:
                    System.out.print("Nhập nhiệt độ muốn set: ");
                    int temp = scanner.nextInt();
                    sensor.setTemperature(temp);
                    break;
                case 3:
                    if (fanRegistered) {
                        sensor.detach(fan);
                        fanRegistered = false;
                        System.out.println("Quạt: Đã hủy đăng ký");
                    } else {
                        System.out.println("Quạt chưa được đăng ký.");
                    }
                    break;
                case 4:
                    if (humidifierRegistered) {
                        sensor.detach(humidifier);
                        humidifierRegistered = false;
                        System.out.println("Máy tạo ẩm: Đã hủy đăng ký");
                    } else {
                        System.out.println("Máy tạo ẩm chưa được đăng ký.");
                    }
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
