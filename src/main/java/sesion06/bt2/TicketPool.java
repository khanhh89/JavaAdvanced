package sesion06.bt2;

import sesion06.bt1.Ticket;

import java.util.ArrayList;
import java.util.List;
public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 0;

    public TicketPool(String roomName, int initialCount) {
        this.roomName = roomName;
        addTickets(initialCount);
    }
    public synchronized Ticket sellTicket() {
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
        System.out.println(">>> Hệ thống: Đã thêm " + count + " vé mới vào phòng " + roomName);
    }

    public String getRoomName() { return roomName; }

    public synchronized int getRemainingTickets() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }
}
