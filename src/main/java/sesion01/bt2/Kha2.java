package sesion01.bt2;

import java.util.Scanner;

public class Kha2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm sinh của bạn: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập vào tổng số người dùng: ");
        int n = sc.nextInt();
        System.out.print(" Nhập số nhóm: ");
        int m = sc.nextInt();
        try{
            int age = 2026 - year;
            System.out.println("Tuổi của bạn là: " + age);
        } catch (NumberFormatException e){
            System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại năm sinh dưới dạng số.");
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
        try{
            int team = n/m;
            System.out.println("So người mỗi nhóm là: "+ team);
        } catch (ArithmeticException e) {
            System.out.println("Không thê chia cho 0!!");
        }
    }
}
