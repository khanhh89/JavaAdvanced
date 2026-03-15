package sesion06.bt3;

import sesion06.bt1.Ticket;

import java.util.ArrayList;
import java.util.List;
public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 0;
    private boolean isSupplierFinished = false;

    public TicketPool(String roomName, int initialCount) {
        this.roomName = roomName;
        addTickets(initialCount);
    }

    public synchronized void setSupplierFinished() {
        this.isSupplierFinished = true;
    }

    public synchronized Ticket sellTicket(String counterName) throws InterruptedException {
        while (getRemainingCount() == 0) {
            if (isSupplierFinished) return null;

            System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
            wait();
        }

        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            ticketCounter++;
            String id = roomName + "-" + String.format("%03d", ticketCounter);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println(">>> Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        notifyAll();
    }
    private int getRemainingCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }

    public String getRoomName() { return roomName; }
}