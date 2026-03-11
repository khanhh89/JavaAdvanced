package sesion04.bt5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {
    PermissionService service;
    User admin;
    User moderator;
    User regularUser;
    @BeforeEach
    void setUp() {
        service = new PermissionService();
        admin = new User(Role.ADMIN, "Admin User");
        moderator = new User(Role.MODERATOR, "Mod User");
        regularUser = new User(Role.USER, "Normal User");
    }

    @AfterEach
    void tearDown() {
        admin = null;
        moderator = null;
        regularUser = null;
    }
    @Test
    void admin_Should_Have_All_Permissions() {
        assertTrue(service.canPerformAction(admin, Action.DELETE_USER));
        assertTrue(service.canPerformAction(admin, Action.LOCK_USER));
        assertTrue(service.canPerformAction(admin, Action.VIEW_PROFILE));
    }
    @Test
    void moderator_Should_Lock_But_Not_Delete() {
        assertFalse(service.canPerformAction(moderator, Action.DELETE_USER)); // Chan xoa
        assertTrue(service.canPerformAction(moderator, Action.LOCK_USER));
        assertTrue(service.canPerformAction(moderator, Action.VIEW_PROFILE));
    }
    @Test
    void user_Should_Only_View_Profile() {
        assertFalse(service.canPerformAction(regularUser, Action.DELETE_USER));
        assertFalse(service.canPerformAction(regularUser, Action.LOCK_USER));
        assertTrue(service.canPerformAction(regularUser, Action.VIEW_PROFILE)); // Chi duoc xem
    }
}
