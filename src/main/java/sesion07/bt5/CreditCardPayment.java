package sesion07.bt5;

class CreditCardPayment implements PaymentMethod {
    @Override public void pay(double amt) { System.out.println("Thanh toán thẻ: " + amt); }
    @Override public String getName() { return "Thẻ tín dụng"; }
}
