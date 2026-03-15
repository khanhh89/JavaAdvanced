package sesion06.bt2;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomA, roomB);
        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 2);

        Thread thread1 = new Thread(c1);
        Thread thread2 = new Thread(c2);
        Thread supplierThread = new Thread(supplier);
        System.out.println("Bắt đầu bán vé");
        thread1.start();
        thread2.start();
        supplierThread.start();

        try {
            thread1.join();
            thread2.join();
            supplierThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Quầy 1 bán: " + c1.getSoldCount());
        System.out.println("Quầy 2 bán: " + c2.getSoldCount());
        System.out.println("Tổng vé còn lại: " + (roomA.getRemainingTickets() + roomB.getRemainingTickets()));
    }
}
