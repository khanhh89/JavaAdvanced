package sesion06.bt5;

import java.util.List;

class TimeoutManager implements Runnable {
    private List<TicketPool> pools;

    public TimeoutManager(List<TicketPool> pools) {
        this.pools = pools;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (TicketPool pool : pools) {
                    pool.releaseExpiredTickets();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("TimeoutManager dừng hoạt động.");
        }
    }
}