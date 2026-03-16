package sesion07.bt5;

class EmailNotification implements NotificationService {
    @Override public void send(String msg, String to) {
        System.out.println("Gửi Email tới " + to + ": " + msg);
    }
}
