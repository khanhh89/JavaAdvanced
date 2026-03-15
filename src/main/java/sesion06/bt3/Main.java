package sesion06.bt3;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 5);
        TicketPool roomB = new TicketPool("B", 5);

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomA, roomB);
        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 2);

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(supplier).start();
    }
}
