package sesion06.bt4;

import sesion06.bt1.Ticket;
import sesion06.bt1.TicketPool;
public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        boolean isSafe = false;

        System.out.println("--- CHƯƠNG TRÌNH BÁN VÉ COMBO ---");
        System.out.println("Chế độ an toàn: " + (isSafe ? "BẬT" : "TẮT (Gây Deadlock)"));

        Thread t1;
        Thread t2;

        if (!isSafe) {
            t1 = new Thread(new ComboBookingCounter("Quầy 1", roomA, roomB));
            t2 = new Thread(new ComboBookingCounter("Quầy 2", roomB, roomA));
        } else {
            t1 = new Thread(new ComboBookingCounter("Quầy 1", roomA, roomB));
            t2 = new Thread(new ComboBookingCounter("Quầy 2", roomA, roomB));
        }
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ComboBookingCounter implements Runnable {
    private String counterName;
    private TicketPool firstPool;
    private TicketPool secondPool;
    public ComboBookingCounter(String name, TicketPool p1, TicketPool p2) {
        this.counterName = name;
        this.firstPool = p1;
        this.secondPool = p2;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (firstPool) {
                System.out.println(counterName + ": Đã giữ khóa " + firstPool.getRoomName());

                try { Thread.sleep(200); } catch (InterruptedException e) {}

                System.out.println(counterName + ": Đang chờ khóa " + secondPool.getRoomName() + "...");

                synchronized (secondPool) {
                    Ticket t1 = firstPool.sellTicket(counterName);
                    Ticket t2 = secondPool.sellTicket(counterName);

                    if (t1 != null && t2 != null) {
                        System.out.println(counterName + " bán COMBO thành công: " +
                                t1.getTicketId() + " & " + t2.getTicketId());
                    } else {
                        System.out.println(counterName + ": Hết vé để lập Combo.");
                        break;
                    }
                }
            }
        }
    }
}