package sesion04.bt1;
import java.util.*;
public class UserValidator {
    public boolean validateUser(String username) {
        if(username.length() < 6 || username.length() > 20){
            return false;
        }
        return true;
    }
}
