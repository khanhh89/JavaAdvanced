package sesion02.Bt3;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
@FunctionalInterface
public interface Authenticatable {
    String getPassword();
    default boolean isAuthenticated(String password) {
        if (password == null) return false;
        String hashedInput = Authenticatable.encryptPassword(password);
        return hashedInput.equals(getPassword());
    }
    static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi thuật toán mã hóa!", e);
        }
    }
}