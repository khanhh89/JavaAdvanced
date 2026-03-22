package sesion02.Bt3;

public class User implements Authenticatable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        // Store the encrypted version of the password
        this.password = Authenticatable.encryptPassword(password);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Authenticatable.encryptPassword(password);
    }
}
