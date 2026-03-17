package sesion08.bt2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        
        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor temperatureSensor = new ThermometerAdapter(oldThermometer);
        SmartHomeFacade facade = new SmartHomeFacade(light, fan, ac, temperatureSensor, oldThermometer);

        while (true) {
            System.out.println("\n1. Xem nhiệt độ");
            System.out.println("2. Chế độ rời nhà");
            System.out.println("3. Chế độ ngủ");
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
                    facade.getCurrentTemperature();
                    break;
                case 2:
                    facade.leaveHome();
                    break;
                case 3:
                    facade.sleepMode();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
