package sesion03.Bt4;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
record User(String username) {}
public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice"),
                new User("bob"),
                new User("alice"),
                new User("alice"),
                new User("alice"),
                new User("charlie")
        );
        Set<String> uniqueUsernames = new HashSet<>();
        List<User> distinctUsers = new ArrayList<>();
        for (User user : users) {
            if (uniqueUsernames.add(user.username())) {
                distinctUsers.add(user);
            }
        }
        System.out.println("Danh sách không trùng lặp:");
        distinctUsers.forEach(u -> System.out.println(u.username()));
    }
}
