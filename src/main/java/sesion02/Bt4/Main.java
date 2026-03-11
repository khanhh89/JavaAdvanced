package sesion02.Bt4;
import java.util.function.*;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                    new User("alice"),
                    new User("bob"),
                    new User("charlie")
            );
            Function<User, String> getUsername = User::getUsername;
            users.forEach(u -> System.out.println("Username: " + getUsername.apply(u)));
            Consumer<String> printString = System.out::println;
            users.stream()
                    .map(User::getUsername)
                    .forEach(printString);
            Supplier<User> newUserSupplier = User::new;
            User defaultUser = newUserSupplier.get();
            System.out.println("Default user: " + defaultUser.getUsername());
        }
    }
    class User {
        private String username;
        public User() {
            this.username = "default";
        }
        public User(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }
    }