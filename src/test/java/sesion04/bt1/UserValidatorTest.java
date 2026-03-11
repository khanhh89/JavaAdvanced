package sesion04.bt1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class UserValidatorTest {
    private final UserValidator validator = new UserValidator();
    @Test
    void TC01_ValidUsername_ShouldReturnTrue() {
        String username = "user123";
        boolean result = validator.isValidUsername(username);
        assertTrue(result, "Username hop le phai tra ve true");
    }
    @Test
    void TC02_UsernameTooShort_ShouldReturnFalse() {
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result, "Username qua ngan phai tra ve false");
    }
    @Test
    void TC03_UsernameContainsSpace_ShouldReturnFalse() {
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result, "Username chua khoang trang phai tra ve false");
    }
}