package sesion03.Bt2;
import java.util.ArrayList;
import java.util.List;
public class StreamFilter {
    public record User (String username, String email, Status Status) {
        public String status() {
            return Status.toString();
        }
    }
    public enum Status {
        Active,
        Inactive
    }
    public static void main(String[] args) {
        User alice = new User("alice", "alice@gmail.com", Status.Active);
        User bob = new User("bob", "bob@gmail.com",  Status.Inactive);
        User charlie = new User("charlie", "charlie",  Status.Active);
        List<User> users = new ArrayList<User>();
        users.add(alice);
        users.add(bob);
        users.add(charlie);
        users.forEach(user ->
                System.out.println(user.username() + " - " + user.email() + " - " + user.status())
        );
        users.stream()
                .filter(user -> user.email().endsWith("@gmail.com"))
                .forEach(user -> System.out.println(user.username()));
    }
}
