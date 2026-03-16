package sesion07.bt3;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        System.out.println("Thanh toan COD");
        PaymentMethod cod = new CODPayment();
        processor.execute(cod, 500000);
        System.out.println("\nThanh toan Card");
        PaymentMethod card = new CreditCardPayment();
        processor.execute(card, 1000000);

        System.out.println("\n LSP");
        PaymentMethod lspMethod = new CreditCardPayment();
        lspMethod = new MomoPayment();

        processor.execute(lspMethod, 750000);
    }
}