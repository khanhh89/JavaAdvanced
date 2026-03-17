package sesion09.ktth;

import java.util.Scanner;

public class ProductFactory {
    public static Product createProduct(int type, Scanner sc){
        System.out.println("Nhap ID: ");
        String id = sc.nextLine();
        System.out.println("Nhap ten: ");
        String name = sc.nextLine();
        System.out.println("Nhap gia: ");
        double price = Double.parseDouble(sc.nextLine());
        if(type == 1){
            System.out.println("Nhap kich thuoc: ");
            double size = Double.parseDouble(sc.nextLine());
            return new DigitalProduct(id, name, price, size);
        }else{
            System.out.println("Nhap can nang: ");
            double weight = Double.parseDouble(sc.nextLine());
            return new PhysicalProduct(id, name, price, weight);
        }
    }
}
