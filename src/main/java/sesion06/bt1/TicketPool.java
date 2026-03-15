package sesion06.bt1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<Ticket>();
    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(roomName + "-"+String.format("%03d", i),roomName));
        }
    }
    public synchronized Ticket sellTicket(String counterName) {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }
    public String getRoomName() { return roomName; }

    public int getRemainingTickets() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }
}
