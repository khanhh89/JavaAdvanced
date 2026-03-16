package sesion07.bt3;

public class CreditCardPayment implements CardPayable {
    @Override
    public void authorize() {
        System.out.println("Da xac thuc the tin dung qua cong ngan hang.");
    }

    @Override
    public void pay(double amount) {
        authorize();
        System.out.println("Xu ly thanh toan the: " + amount + " - Thanh cong");
    }
}