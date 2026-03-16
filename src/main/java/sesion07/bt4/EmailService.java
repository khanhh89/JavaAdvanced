package sesion07.bt4;

class EmailService implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email tới " + recipient + ": " + message);
    }
}