package sesion01.bt6;
import java.io.IOException;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        try {
            User user = new User("Nguyen Van A", "invalidEmail");
            user.setAge(-5);
            UserService.processUserData(user);
            System.out.println("Dữ liệu đã được xử lý thành công!");
        } catch (InvalidEmailException e) {
            logError("Lỗi nghiệp vụ Email: " + e.getMessage());
        } catch (InvalidAgeException e) {
            logError("Lỗi nghiệp vụ Tuổi: " + e.getMessage());
        } catch (IOException e) {
            logError("Lỗi IO: " + e.getMessage());
        }
    }
    private static void logError(String msg) {
        System.out.println("[ERROR] " + LocalDate.now() + " - " + msg);
    }
}

