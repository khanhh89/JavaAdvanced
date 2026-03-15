package sesion06.bt3;

public class TicketSupplier implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;
    private int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(3000);
                roomA.addTickets(3);
                roomB.addTickets(3);
            }
            roomA.setSupplierFinished();
            roomB.setSupplierFinished();
            System.out.println("=== Nhà cung cấp đã hoàn tất tất cả các đợt bổ sung ===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
