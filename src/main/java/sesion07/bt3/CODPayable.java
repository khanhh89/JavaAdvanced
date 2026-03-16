package sesion07.bt3;
public interface CODPayable extends PaymentMethod {
    void validateAddress();

    void processPayment(double amount);
}
