package sesion06.bt5;

import java.util.List;
import java.util.Random;
class BookingCounter implements Runnable {
    private String name;
    private List<TicketPool> pools;
    private boolean willPay;

    public BookingCounter(String name, List<TicketPool> pools, boolean willPay) {
        this.name = name;
        this.pools = pools;
        this.willPay = willPay;
    }

    @Override
    public void run() {
        TicketPool pool = pools.get(new Random().nextInt(pools.size()));
        Ticket t = pool.holdTicket();

        if (t != null) {
            System.out.println(name + ": Đã giữ vé " + t.getTicketId() + ". Vui lòng thanh toán trong 5s.");

            try {
                Thread.sleep(3000);
                if (willPay) {
                    if (pool.sellHeldTicket(t)) {
                        System.out.println(name + ": Thanh toán THÀNH CÔNG vé " + t.getTicketId());
                    } else {
                        System.out.println(name + ": Thanh toán THẤT BẠI (vé đã bị thu hồi).");
                    }
                } else {
                    System.out.println(name + ": Khách hàng bỏ đi, không thanh toán.");
                    // Chờ để TimeoutManager tự thu hồi
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        } else {
            System.out.println(name + ": Hết vé tại phòng " + pool.getRoomName());
        }
    }
}