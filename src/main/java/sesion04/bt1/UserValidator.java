package sesion04.bt1;
import java.util.*;
public class UserValidator {
    public boolean isValidUsername(String username) {
        if (username == null) return false;
        int length = username.length();
        if (length < 6 || length > 20) {
            return false;
        }
        if (username.contains(" ")) {
            return false;
        }
        return true;
    }
}
