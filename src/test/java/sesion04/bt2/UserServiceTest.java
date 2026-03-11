package sesion04.bt2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class UserServiceTest {
    private final UserService userService = new UserService();
    @Test
    void testBoundaryAge_18_ShouldReturnTrue() {
        boolean result = userService.checkRegistrationAge(18);
        assertEquals(true, result, "18 tuoi phai duoc phep dang ky");
    }

    @Test
    void testUnderAge_17_ShouldReturnFalse() {
        boolean result = userService.checkRegistrationAge(17);
        assertEquals(false, result, "17 tuoi khong duoc phep dang ky");
    }

    @Test
    void testNegativeAge_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(-1);
        }, "Tuoi am phai nem ra IllegalArgumentException");
    }
}