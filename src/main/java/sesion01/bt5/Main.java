package sesion01.bt5;
import sesion01.bt3.User;

public class Main {
    public static void main(String[] args) {
        try {
            User user = new User("Khanhdao");
            user.setAge(-25);
            System.out.println("Tuổi người dùng" + user.getAge());
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
