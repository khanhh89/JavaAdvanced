package sesion04.bt4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {
    private final PasswordService service = new PasswordService();
    @Test
    void testPasswordStrength_Matrix() {
        assertAll("Kiem tra ma tran do manh mat khau",
                () -> assertEquals("Manh", service.evaluatePasswordStrength("Abc123!@"), "TC01 Failed"),
                () -> assertEquals("Trung binh", service.evaluatePasswordStrength("abc123!@"), "TC02 Failed (Thieu hoa)"),
                () -> assertEquals("Trung binh", service.evaluatePasswordStrength("ABC123!@"), "TC03 Failed (Thieu thuong)"),
                () -> assertEquals("Trung binh", service.evaluatePasswordStrength("Abcdef!@"), "TC04 Failed (Thieu so)"),
                () -> assertEquals("Trung binh", service.evaluatePasswordStrength("Abc12345"), "TC05 Failed (Thieu dac biet)"),
            () -> assertEquals("Yeu", service.evaluatePasswordStrength("Ab1!"), "TC06 Failed (Qua ngan)"),
                () -> assertEquals("Yeu", service.evaluatePasswordStrength("password"), "TC07 Failed (Chi co chu thuong)"),
                () -> assertEquals("Yeu", service.evaluatePasswordStrength("ABC12345"), "TC08 Failed (Thieu chu thuong va dac biet)")
        );
    }
}