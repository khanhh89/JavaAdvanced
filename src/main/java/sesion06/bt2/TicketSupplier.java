package sesion06.bt2;

public class TicketSupplier implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;
    private int supplyCount; // Số vé thêm mỗi lần
    private int interval;    // Thời gian nghỉ (ms)
    private int rounds;      // Số lần cung cấp

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(interval); // Nghỉ định kỳ (ví dụ 3000ms)
                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
            }
            System.out.println("=== Nhà cung cấp đã hoàn tất các đợt bổ sung vé ===");
        } catch (InterruptedException e) {
            System.out.println("Nhà cung cấp vé bị ngắt quãng.");
        }
    }
}
