package sesion04.bt5;
import java.util.ArrayList;
import java.util.List;
enum Role { ADMIN, MODERATOR, USER }
enum Action { DELETE_USER, LOCK_USER, VIEW_PROFILE }
class User {
    Role role;
    String name;

    User(Role role, String name) {
        this.role = role;
        this.name = name;
    }
}
class PermissionService {
    public boolean canPerformAction(User user, Action action) {
        if (user == null || user.role == null) return false;
        switch (user.role) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action != Action.DELETE_USER;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}