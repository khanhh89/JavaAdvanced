package sesion07.bt3;

public class PaymentProcessor {
    public void execute(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}