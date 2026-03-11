package sesion01.bt6;

public class User {
    private String name;
    private int age;
    private String email;
    public User(String name, String email) throws InvalidEmailException {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("Email không hợp lệ: " + email);
        }
        this.email = email;
    }
    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Tuổi không hợp lệ: " + age);
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
