package sesion02.Bt6;
public class Main {
    public static void main(String[] args) {
        User myUser = new User("Khanhdao");
        UserProcessor processor = UserUtils::convertToUpperCase;
        System.out.println(processor.processUser(String.valueOf(myUser)));
    }
}