package sesion02.Bt6;

public class UserUtils {
    public static String convertToUpperCase (User u) {
        if(u==null||u.getUsername()==null){
            return "";
        }else {
            return u.getUsername().toUpperCase();
        }
    }


    public static String convertToUpperCase(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        return s;
    }
}
