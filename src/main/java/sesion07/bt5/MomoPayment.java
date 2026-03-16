package sesion07.bt5;

class MomoPayment implements PaymentMethod {
    @Override public void pay(double amt) { System.out.println("Thanh toán MoMo: " + amt); }
    @Override public String getName() { return "Ví MoMo"; }
}