package sesion06.bt2;

import sesion06.bt1.Ticket;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        while (true) {
            TicketPool target = random.nextBoolean() ? roomA : roomB;
            Ticket t = target.sellTicket();

            if (t == null) {
                TicketPool other = (target == roomA) ? roomB : roomA;
                t = other.sellTicket();
            }

            if (t != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + t.getTicketId());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            } else {
                try { Thread.sleep(500); } catch (InterruptedException e) {}
                if (roomA.getRemainingTickets() == 0 && roomB.getRemainingTickets() == 0) {
                    break;
                }
            }
        }
    }
    public int getSoldCount() { return soldCount; }
}
