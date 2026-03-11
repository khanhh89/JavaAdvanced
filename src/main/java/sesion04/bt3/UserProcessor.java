package sesion04.bt3;

public class UserProcessor {
    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email khong duoc null");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email thieu ky tu @");
        }
        String[] parts = email.split("@");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Email thieu ten mien");
        }
        return email.toLowerCase();
    }
}