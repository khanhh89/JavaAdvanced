package sesion07.bt5;

class InvoiceGenerator {
    public static void print(Order o) {
        System.out.println("\n=== HÓA ĐƠN ===");
        System.out.println("Khách hàng: " + o.customer.name);
        o.products.forEach(p -> System.out.println("- " + p.name + ": " + p.price));
        System.out.println("Tổng: " + o.totalAmount + " | Sau giảm giá: " + o.finalAmount);
    }
}