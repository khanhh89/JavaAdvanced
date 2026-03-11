package sesion02.Bt5;
interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User Log: " + activity);
    }
}
interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin Log: " + activity);
    }
}
class SuperAdmin implements UserActions, AdminActions {
    @Override
    public void logActivity(String activity) {
        System.out.println("Giải quyết xung đột");
        AdminActions.super.logActivity(activity);
        System.out.println("SuperAdmin ghi nhận: " + activity);
    }
}

public class Main {
    public static void main(String[] args) {
        SuperAdmin sa = new SuperAdmin();
        sa.logActivity("Cấu hình lại toàn bộ hệ thống");
    }
}