package sesion06.bt1;

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
            TicketPool targetPool;
            if(random.nextBoolean()) {
                targetPool = roomA;
            }else  {
                targetPool = roomB;
            }

            Ticket soldTicket = targetPool.sellTicket(counterName);
            if(soldTicket == null) {
                TicketPool otherPool = (targetPool == roomA) ? roomB : roomA;
                soldTicket = otherPool.sellTicket(counterName);
            }
            if(soldTicket == null) {
                break;
            }
            soldCount++;
            System.out.println(counterName+ " bán vé phòng " + soldTicket.getRoomName()+" :"+soldTicket.getTicketId());
            try {
                Thread.sleep(50);
            }catch (InterruptedException e) {

            }
        }
    }
    public int getSoldCount() {
        return soldCount;
    }
}
