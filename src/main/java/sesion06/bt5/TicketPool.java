package sesion06.bt5;

import java.util.*;

class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
    }

    public synchronized Ticket holdTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold() && !t.isHeld()) {
                t.setHeld(true);
                t.setHoldExpiryTime(System.currentTimeMillis() + 5000);
                return t;
            }
        }
        return null;
    }

    public synchronized boolean sellHeldTicket(Ticket t) {
        if (t != null && t.isHeld() && !t.isSold()) {
            t.setSold(true);
            t.setHeld(false);
            return true;
        }
        return false;
    }

    public synchronized void releaseExpiredTickets() {
        long now = System.currentTimeMillis();
        for (Ticket t : tickets) {
            if (t.isHeld() && !t.isSold() && now > t.getHoldExpiryTime()) {
                t.setHeld(false);
                t.setHoldExpiryTime(0);
                System.out.println(">>> TimeoutManager: Vé " + t.getTicketId() + " hết hạn, đã trả lại kho.");
            }
        }
    }

    public String getRoomName() { return roomName; }
}