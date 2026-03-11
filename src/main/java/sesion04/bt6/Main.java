package sesion04.bt6;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class User{
    String name;
    String email;
    String dob;
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
class ProfileService{
    public User updateProfile(User existingUser, String newDob,  String newEmail, List<User> users){
        if(LocalDate.parse(newDob).isAfter(LocalDate.now())){
            return null;
        }
        if (!newEmail.equals(existingUser.email)) {
            for(User u: users){
                if(u.email.equals(newEmail)){
                    return null;
                }
            }
        }
        existingUser.email = newEmail;
        existingUser.dob = newDob;
        return existingUser;
    }
}
public class Main {
    public static void main(String[] args) {
        ProfileService service = new ProfileService();
        User existingUser = new User( "khanh", "khanh@gmail.com");
        User otherUser = new User( "Trung", "trung@gmail.com");
        List<User> allUsers = Arrays.asList(existingUser, otherUser);
        User result1 = service.updateProfile(existingUser, "2099-01-01", "new@gmail.com", allUsers);
        System.out.println("Test Ngày sinh tương lai: " + (result1 == null ? "PASS (Bị chặn)" : "FAIL"));
       User result2 = service.updateProfile(existingUser, "1995-01-01", "trung@gmail.com", allUsers);
        System.out.println("Test Trùng Email: " + (result2 == null ? "PASS (Bị chặn)" : "FAIL"));
        User result3 = service.updateProfile(existingUser, "1995-01-01", "cuong_moi@gmail.com", allUsers);
        System.out.println("Test Hợp lệ: " + (result3 != null ? "PASS (Cập nhật xong)" : "FAIL"));
    }
}
