package sesion04.bt3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserProcessorTest {

    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
        System.out.println("Da khoi tao UserProcessor cho bai test moi.");
    }

    @Test
    void should_ReturnEmail_When_FormatIsValid() {
        String input = "user@gmail.com";
        String result = processor.processEmail(input);
        assertEquals("user@gmail.com", result, "Email hop le phai giu nguyen dinh dang");
    }
    @Test
    void should_ThrowException_When_AtSignIsMissing() {
        // Arrange
        String input = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        }, "Phai nem loi khi thieu ky tu @");
    }

    @Test
    void should_ThrowException_When_DomainIsMissing() {
        String input = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        }, "Phai nem loi khi co @ nhung thieu ten mien");
    }

    @Test
    void should_ReturnLowercaseEmail_When_InputHasUppercase() {
        String input = "Example@Gmail.com";
        String result = processor.processEmail(input);
        assertEquals("example@gmail.com", result, "Email phai duoc chuyen ve chu thuong");
    }
}
