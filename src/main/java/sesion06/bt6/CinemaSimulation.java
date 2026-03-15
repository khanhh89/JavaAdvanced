package sesion06.bt6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
class Ticket {
    private String id;
    private String roomName;
    private boolean isSold = false;

    public Ticket(String id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }
    public String getId() { return id; }
    public String getRoomName() { return roomName; }
    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }
}
class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition isNotPaused = lock.newCondition();
    private boolean paused = false;

    public TicketPool(String name, int count) {
        this.roomName = name;
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(name + "-" + String.format("%03d", i), name));
        }
    }

    public void setPaused(boolean status) {
        lock.lock();
        try {
            this.paused = status;
            if (!status) isNotPaused.signalAll();
        } finally { lock.unlock(); }
    }

    public Ticket sellTicket(String counterName) throws InterruptedException {
        lock.lock();
        try {
            while (paused) {
                System.out.println(counterName + " đang tạm nghỉ...");
                isNotPaused.await();
            }
            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    t.setSold(true);
                    return t;
                }
            }
            return null;
        } finally { lock.unlock(); }
    }

    public String getRoomName() { return roomName; }
    public int getSoldSize() { return (int) tickets.stream().filter(Ticket::isSold).count(); }
    public int getTotalSize() { return tickets.size(); }
}

class BookingCounter implements Runnable {
    private String name;
    private List<TicketPool> pools;
    private Random random = new Random();

    public BookingCounter(String name, List<TicketPool> pools) {
        this.name = name;
        this.pools = pools;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                TicketPool pool = pools.get(random.nextInt(pools.size()));
                Ticket t = pool.sellTicket(name);

                if (t != null) {
                    System.out.println(name + " đã bán thành công vé: " + t.getId());
                    Thread.sleep(800); // Tốc độ bán vé
                } else {
                    boolean stillHasTickets = pools.stream().anyMatch(p -> p.getSoldSize() < p.getTotalSize());
                    if (!stillHasTickets) break;
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " đã ngừng hoạt động.");
        }
    }
}
public class CinemaSimulation {
    private static List<TicketPool> pools = new ArrayList<>();
    private static ExecutorService executor;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê doanh thu");
            System.out.println("6. Quét và phát hiện Deadlock");
            System.out.println("7. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: startSimulation(); break;
                case 2: setPause(true); break;
                case 3: setPause(false); break;
                case 5: showStats(); break;
                case 6: detectDeadlock(); break;
                case 7: stopSystem(); return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void startSimulation() {
        System.out.print("Nhập số phòng chiếu: ");
        int numRooms = scanner.nextInt();
        System.out.print("Nhập số vé mỗi phòng: ");
        int numTickets = scanner.nextInt();
        System.out.print("Nhập số quầy bán vé: ");
        int numCounters = scanner.nextInt();

        pools.clear();
        for (int i = 0; i < numRooms; i++) {
            pools.add(new TicketPool("Phòng " + (char) ('A' + i), numTickets));
        }

        executor = Executors.newFixedThreadPool(numCounters);
        for (int i = 1; i <= numCounters; i++) {
            executor.execute(new BookingCounter("Quầy " + i, pools));
        }
        System.out.println(">>> Đã khởi chạy " + numCounters + " quầy bán vé...");
    }

    private static void setPause(boolean status) {
        if (pools.isEmpty()) return;
        for (TicketPool p : pools) p.setPaused(status);
        System.out.println(status ? "--- ĐÃ TẠM DỪNG HỆ THỐNG ---" : "--- TIẾP TỤC HOẠT ĐỘNG ---");
    }

    private static void showStats() {
        if (pools.isEmpty()) {
            System.out.println("Chưa có dữ liệu mô phỏng.");
            return;
        }
        System.out.println("\n--- BÁO CÁO DOANH THU ---");
        int totalSold = 0;
        for (TicketPool p : pools) {
            System.out.printf("%s: [%-10s] %d/%d vé\n",
                    p.getRoomName(),
                    "|".repeat(p.getSoldSize() * 10 / p.getTotalSize()),
                    p.getSoldSize(), p.getTotalSize());
            totalSold += p.getSoldSize();
        }
        System.out.println("Tổng doanh thu: " + (totalSold * 75000) + " VNĐ");
    }

    private static void detectDeadlock() {
        System.out.println("Đang quét hệ thống...");
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] ids = bean.findDeadlockedThreads();
        if (ids != null) {
            System.err.println("NGUY HIỂM: Phát hiện Deadlock tại " + ids.length + " luồng!");
            for (ThreadInfo info : bean.getThreadInfo(ids)) {
                System.err.println("- Luồng bị treo: " + info.getThreadName());
            }
        } else {
            System.out.println("Kết quả: Hệ thống hoạt động an toàn (No Deadlock).");
        }
    }

    private static void stopSystem() {
        if (executor != null) executor.shutdownNow();
        System.out.println("Đang đóng các quầy... Tạm biệt!");
    }
}