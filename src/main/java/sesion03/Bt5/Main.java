package sesion03.Bt5;
import java.util.*;
import java.util.stream.Collectors;
record User(String username) {}
public class Main {
        public static void main(String[] args) {
            List<User> users = List.of(
                    new User("alexander"),
                    new User("bob"),
                    new User("charlotte"),
                    new User("Benjamin"),
                    new User("amy"),
                    new User("christopher")
            );
            List<User> top3 = users.stream()
                    .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            System.out.println("3 người dùng có username dài nhất:");
            top3.forEach(u -> System.out.println(u.username()));
        }
    }