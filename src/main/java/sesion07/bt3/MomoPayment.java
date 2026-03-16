package sesion07.bt3;

public class MomoPayment implements EWalletPayable {
    @Override
    public void conectApp() {
        System.out.println("Da ket noi va xac thuc vi MoMo.");
    }

    @Override
    public void pay(double amount) {
        conectApp();
        System.out.println("Xu ly thanh toan MoMo: " + amount + " - Thanh cong");
    }
}