package sesion02.Bt2;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        PasswordValidator validPass = password -> password.length() >= 8;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập password: ");
        password = sc.nextLine();
        if (validPass.validate(password)) {
            System.out.println("password hợp lệ");
        }else  {
            System.out.println("password không hợp lệ");
        }
    }
}

@FunctionalInterface
interface PasswordValidator {
    boolean validate(String password);
}