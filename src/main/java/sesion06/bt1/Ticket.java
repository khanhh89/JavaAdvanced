package sesion06.bt1;

public class Ticket {
    private String ticketId;
    private String roomName;
    private boolean isSold;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getRoomName() {
        return roomName;
    }
    public void setSold(boolean sold) {
        this.isSold = sold;
    }
    public boolean isSold() {
        return isSold;
    }
}