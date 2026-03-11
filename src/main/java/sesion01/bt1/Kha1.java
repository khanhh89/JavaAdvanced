package sesion01.bt1;
import java.util.Scanner;
public class Kha1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm sinh của bạn: ");
        try{
            int year = Integer.parseInt(sc.nextLine());
            int age = 2026 - year;
            System.out.println("Tuổi của bạn là: " + age);
        } catch (NumberFormatException e){
            System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại năm sinh dưới dạng số.");
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
        sc.close();
    }
}
