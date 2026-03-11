package sesion03.Bt3;

import java.util.ArrayList;
import java.util.List;

public class Optional<U> {
    public static class User{
        private String username;
        public User(String username){
            this.username = username;
        }
        public String getUsername(){
            return username;
        }
    }
    public static class UserRepository{
        private List<User> users;
        public UserRepository(){
            users = new ArrayList<>();
            users.add(new User("alice"));
            users.add(new User("bob"));
            users.add(new User("charlie"));
        }
        public java.util.Optional<User> findUserByUsername(String username){
            return users.stream().filter(u->u.getUsername().equalsIgnoreCase(username)).findFirst();
        }
    }
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        repo.findUserByUsername("alice").ifPresentOrElse(u -> System.out.println("Welcome "+ u.getUsername()),()-> System.out.println("Guest login"));
    }
}
