package sesion01.bt6;

import java.io.IOException;

public class UserService {
    public static void processUserData(User user) throws IOException, InvalidAgeException {
        if (user == null) {
            throw new IOException("User không tồn tại!");
        }

        // Defensive: kiểm tra trước khi in ra
        String name = (user.getName() != null) ? user.getName() : "Unknown";

        String data = "Tên: " + name + ", Tuổi: " + user.getAge() + ", Email: " + user.getEmail();
        FileUtil.saveToFile(data);
    }
}
