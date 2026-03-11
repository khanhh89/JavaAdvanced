package sesion02.Bt3;

import java.util.Scanner;

public class User implements Authenticatable {
    private String password;
    public User(String password) {
        this.password = password;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        String rawPassword = password;
        String hashed = Authenticatable.encryptPassword(rawPassword);
        User user = new User(hashed);
        boolean result = user.isAuthenticated(password);
        System.out.println("Mật khẩu thô: " + rawPassword);
        System.out.println("Mật khẩu mã hóa: " + user.getPassword());
        System.out.println("Kết quả đăng nhập: " + (result ? "Thành công" : "Thất bại"));
    }
}