package sesion06.bt3;

import sesion06.bt1.Ticket;
import java.util.*;
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
        try {
            while (true) {
                TicketPool target = random.nextBoolean() ? roomA : roomB;
                Ticket t = target.sellTicket(counterName);

                if (t == null) {
                    TicketPool other = (target == roomA) ? roomB : roomA;
                    t = other.sellTicket(counterName);
                }

                if (t != null) {
                    soldCount++;
                    System.out.println(counterName + " đã bán vé " + t.getTicketId());
                    Thread.sleep(200);
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(counterName + " bị ngắt quãng.");
        }
        System.out.println(counterName + " ĐÃ ĐÓNG CỬA.");
    }

    public int getSoldCount() { return soldCount; }
}
