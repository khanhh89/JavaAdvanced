package sesion07.bt3;

public class CODPayment implements CODPayable {
    @Override
    public void validateAddress() {
        System.out.println("Xac thuc dia chi giao hang");
    }

    @Override
    public void processPayment(double amount) {
        validateAddress();
        System.out.println("Xu ly thanh toan: "+amount+" thanh cong");
    }

    @Override
    public void pay(double amount) {

    }
}
